![](docs/logo/project_logo.png)

# java-sc-client (JMantic)

[![Build and test](https://github.com/artrayme/JMantic/actions/workflows/build-test-coverage.yml/badge.svg)](https://github.com/artrayme/JMantic/actions/workflows/build-test-coverage.yml)

[![Coverage](.github/badges/jacoco.svg)](https://github.com/artrayme/JMantic/actions/workflows/build-test-coverage.yml)

## Overview

The goal of this project is to create a java-library for connecting to a sc-machine via a WebSocket interface by using
json format.

****

## Main project priorities:

* **Easy-to-use**. The public api uses only basic abstractions.All classes and methods are documented. There is a wide
  list of usage examples.
* **Easy to support**. The minimum number of required third-party libraries was used for the implementation. The code
  uses modern features of the java language, the files are clearly structured. Also, internal classes and mechanisms are
  partially have documentation.
* **Stability and reliability**. High test coverage. Multi-level logging of events in the system. Hierarchy of
  informative exceptions for each architectural layer.  _(in progress)_

It is important to note that at this stage of project development there is no goal to make a fast library (due to the
technical limitations of sc-machine, as well as a deviation from the main goals of the project). Also, right now the
project does not aim to implement all the available sc-machine functionality due to the small amount of development
time.

****

## Package

If you want to use JMantic in your programs, follow these steps

### Gradle

First, you should add repo to the repositories section.

For example:

```groovy
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/artrayme/jmantic")
        credentials {
            username = "github_username"
            password = "github_PAT_with_access_to_packages_reading"
        }
    }
}
```

Where GitHub_username it is you username in GitHub. Password - your GitHub personal access token. To get such a token,
you need to go to GitHub settings, then go to Developer settings, where in the Personal Access Token section click
Generate new token. In the generation menu, you only need to select the read:packages checkbox. The generated token
needs to be inserted into the password field in the above example.

More information about PAT

- [github docs](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)

Dependency:

```groovy
implementation 'org.ostis:jmantic:0.3.1'
```

### Maven

The first step is to change the settings.xml file in the ~/.m2 folder

```xml

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <activeProfiles>
        <activeProfile>github</activeProfile>
    </activeProfiles>

    <profiles>
        <profile>
            <id>github</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>https://repo1.maven.org/maven2</url>
                </repository>
                <repository>
                    <id>github</id>
                    <url>https://maven.pkg.github.com/artrayme/JMantic</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
        </profile>
    </profiles>

    <servers>
        <server>
            <id>github</id>
            <username>github_username</username>
            <password>github_PAT_with_access_to_packages_reading</password>
        </server>
    </servers>
</settings>
```

Where GitHub_username it is you username in GitHub. Password - your GitHub personal access token. To get such a token,
you need to go to GitHub settings, then go to Developer settings, where in the Personal Access Token section click
Generate new token. In the generation menu, you only need to select the read:packages checkbox. The generated token
needs to be inserted into the password field in the above example.

More information about PAT

- [github docs](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)

Dependency

```xml

<dependency>
    <groupId>org.ostisorg.ostis</groupId>
    <artifactId>jmantic</artifactId>
    <version>0.3.1</version>
</dependency>
```

****

## Ostis configuration

To use JMantic, you need ostis version 0.6.0. You can run ostis any way you want. For example, you can run ostis in the
docker with the following command:

```shell
docker run -it -p 8000:8000 -p 8090:8090 ostis/ostis:0.6.0 sh ostis --all
```

If you do not need to build the base, you can speed up the startup with the following command

```shell
docker run -it -p 8000:8000 -p 8090:8090 ostis/ostis:0.6.0 sh ostis --sctp --web
```

More information about dockerized ostis - [GitHub repository](https://github.com/ostis-apps/dockerized-ostis)

****

## Used libraries

* [Java-WebSocket](https://github.com/TooTallNate/Java-WebSocket)
* [JUnit5](https://github.com/junit-team/junit5)
* [Jackson-databind](https://github.com/FasterXML/jackson-databind)

## Related technologies

* [WebSocket](https://sookocheff.com/post/networking/how-do-websockets-work/)
* [json](https://www.w3schools.com/whatis/whatis_json.asp)
* [sc-machine](http://ostis-dev.github.io/sc-machine/)
* [sc-machine-WebSocket-protocol](http://ostis-dev.github.io/sc-machine/http/websocket/)

