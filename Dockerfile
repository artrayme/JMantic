FROM ubuntu:18.04

# Java 17 installing
ENV JAVA_HOME=/opt/java/openjdk
COPY --from=eclipse-temurin:17-jdk $JAVA_HOME $JAVA_HOME
ENV PATH="${JAVA_HOME}/bin:${PATH}"


# Add sudo user
RUN apt-get update && apt-get install -y sudo

USER root

# Getting dependencies
RUN sudo apt-get update && apt-get -y install git redis-server python-pip python3 qtbase5-dev curl \
    && sudo rm -rf /var/lib/apt/lists/*

WORKDIR /ostis

## Clone projects
RUN git clone --single-branch --branch 0.6.0 https://github.com/ShunkevichDV/ostis.git . && \
    git clone --single-branch --branch 0.6.0 https://github.com/ShunkevichDV/sc-machine.git && \
    git clone --single-branch --branch 0.6.0 https://github.com/ostis-apps/sc-web.git && \
    git clone --single-branch --branch 0.6.0 https://github.com/ShunkevichDV/ims.ostis.kb.git

### sc-machine
WORKDIR /ostis/sc-machine/scripts
RUN python3Version=$(python3 -c 'import sys; print(".".join(map(str, sys.version_info[:2])))') && \
    sudo sed -i -e "s/python3.5-dev/python${python3Version}-dev/" ./install_deps_ubuntu.sh && \
    sudo sed -i -e "s/python3.5-dev/python${python3Version}/" ./install_deps_ubuntu.sh && \
    sudo apt-get update && echo y | sudo ./install_deps_ubuntu.sh && \
    sudo rm -rf /var/lib/apt/lists/*

WORKDIR /ostis/sc-machine

RUN pip3 install -r requirements.txt

WORKDIR /ostis/sc-machine/scripts
RUN sudo ./make_all.sh
RUN cat ../bin/config.ini | sudo tee -a ../../config/sc-web.ini

### sc-server web
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add - && \
    echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list && \
    sudo apt-get update && sudo apt-get install -y yarn && sudo rm -rf /var/lib/apt/lists/*

WORKDIR /ostis/sc-machine/web/client
RUN sudo yarn

## Copy server.conf
WORKDIR /ostis/scripts
RUN sudo cp -f ../config/server.conf ../sc-web/server/

# Include kb
WORKDIR /ostis
RUN sudo mkdir kb && sudo mv ./ims.ostis.kb/ui/ui_start_sc_element.scs ./kb/ui_start_sc_element.scs && \
    sudo mv ./ims.ostis.kb/ui/menu ./kb && echo "kb" | sudo tee -a ./repo.path && sudo mkdir -p problem-solver/cxx && \
    echo "problem-solver" | sudo tee -a ./repo.path


# Gradle installing
CMD ["gradle"]
ENV GRADLE_HOME /opt/gradle
RUN set -o errexit -o nounset \
    && echo "Adding gradle user and group" \
    && groupadd --system --gid 1000 gradle \
    && useradd --system --gid gradle --uid 1000 --shell /bin/bash --create-home gradle \
    && mkdir /home/gradle/.gradle \
    && chown --recursive gradle:gradle /home/gradle \
    \
    && echo "Symlinking root Gradle cache to gradle Gradle cache" \
    && ln --symbolic /home/gradle/.gradle /root/.gradle

VOLUME /home/gradle/.gradle
WORKDIR /home/gradle
RUN set -o errexit -o nounset \
    && apt-get update \
    && apt-get install --yes --no-install-recommends \
        unzip \
        wget \
        \
        bzr \
        git \
        git-lfs \
        mercurial \
        openssh-client \
        subversion \
    && rm --recursive --force /var/lib/apt/lists/* \
    \
    && echo "Testing VCSes" \
    && which bzr \
    && which git \
    && which git-lfs \
    && which hg \
    && which svn

ENV GRADLE_VERSION 7.2
ARG GRADLE_DOWNLOAD_SHA256=f581709a9c35e9cb92e16f585d2c4bc99b2b1a5f85d2badbd3dc6bff59e1e6dd
RUN set -o errexit -o nounset \
    && echo "Downloading Gradle" \
    && wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
    \
    && echo "Checking download hash" \
    && echo "${GRADLE_DOWNLOAD_SHA256} *gradle.zip" | sha256sum --check - \
    \
    && echo "Installing Gradle" \
    && unzip gradle.zip \
    && rm gradle.zip \
    && mv "gradle-${GRADLE_VERSION}" "${GRADLE_HOME}/" \
    && ln --symbolic "${GRADLE_HOME}/bin/gradle" /usr/bin/gradle \
    \
    && echo "Testing Gradle installation" \
    && gradle --version

# JMantic cloning
WORKDIR /
RUN git clone https://github.com/artrayme/JMantic.git
WORKDIR /JMantic

EXPOSE 8090
#EXPOSE 8000
#EXPOSE 55770
