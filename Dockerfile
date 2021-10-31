FROM ubuntu:18.04

#
# Creating image
#
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

### sc-web
#WORKDIR /ostis/sc-web/scripts

#Install sc-web dependencies
# RUN sudo pip install --default-timeout=100 future tornado sqlalchemy redis==2.9 numpy configparser && \
#    sudo apt-get update && apt-get --no-install-recommends install -y nodejs-dev node-gyp npm libssl1.0-dev && \
#    sudo rm -rf /var/lib/apt/lists/* && sudo npm install -g grunt-cli && npm install && sudo grunt build

## Copy server.conf
WORKDIR /ostis/scripts
RUN sudo cp -f ../config/server.conf ../sc-web/server/

# Include kb
WORKDIR /ostis
RUN sudo mkdir kb && sudo mv ./ims.ostis.kb/ui/ui_start_sc_element.scs ./kb/ui_start_sc_element.scs && \
    sudo mv ./ims.ostis.kb/ui/menu ./kb && echo "kb" | sudo tee -a ./repo.path && sudo mkdir -p problem-solver/cxx && \
    echo "problem-solver" | sudo tee -a ./repo.path

# Include kpm
#WORKDIR /ostis/sc-machine
# RUN sudo apt-get update && sudo apt-get --no-install-recommends install -y libcurl4-openssl-dev && \
#    echo 'add_subdirectory(${SC_MACHINE_ROOT}/../problem-solver/cxx ${SC_MACHINE_ROOT}/bin)' | sudo tee -a ./CMakeLists.txt

# Copy start container script
#COPY scripts/ostis /ostis/scripts/


WORKDIR /
RUN git clone https://github.com/artrayme/JMantic.git
WORKDIR /JMantic
RUN sudo bash gradlew

#
# Image config
#
LABEL version="0.6.0"

EXPOSE 8090
EXPOSE 8000
EXPOSE 55770
