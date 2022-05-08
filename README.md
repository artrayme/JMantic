[//]: # (404 not found)

<p align="center">
  <img src="./docs/logo/project_logo.png" alt="drawing" width="400"/>
</p>

---

<p align="center">
  <a href="https://github.com/artrayme/JMantic/actions/workflows/ci-build.yml">
    <img src="https://github.com/artrayme/JMantic/actions/workflows/ci-build.yml/badge.svg"/>
  </a>
  <a href="https://sonarcloud.io/summary/new_code?id=artrayme_JMantic">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=artrayme_JMantic&metric=alert_status"/>
  </a>
  <a href="https://sonarcloud.io/summary/new_code?id=artrayme_JMantic">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=artrayme_JMantic&metric=coverage"/>
  </a>
</p>

# java-sc-client (JMantic)

## Overview
The global goal of this project is to create java-interface for using any sc-memory implementations.
But for a current moment,
the goal of this project is
to create a java-library for connecting to a sc-machine via a WebSocket interface by using json format.

[Documentation in Russian](docs/documentation/ru/JMantic_Documentation_Ru_0.6.0.pdf)
****

## Main project priorities:

* **Easy-to-use**. The public api must be easy to understand. All public-api classes should use basic domain abstractions and have comprehensive documentation.
* **Stability and reliability**. The Project must have high test coverage (80+%). Also, project should have multi-level logging of events in the system and hierarchy of informative exceptions for each architectural layer.
* **Modern language features**. The project must use the latest lts-versions of java and not limit itself to new features of the language
* **Easy to support**. The minimum number of required third-party libraries was used for the implementation. 

It is important to note that at this stage of project development there is no goal to make a fast library (due to the technical limitations of the sc-machine, as well as a deviation from the main goals of the project).

****

## Package

If you want to use JMantic in your programs, follow these steps:

### Gradle

New way since 0.6.2:

``` groovy
  implementation 'io.github.artrayme:jmantic:0.6.3'
```

Old way:

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
implementation 'org.ostis:jmantic:0.6.1'
```

### Maven

New way since 0.6.2:

```xml
<dependency>
    <groupId>org.ostis</groupId>
    <artifactId>jmantic</artifactId>
    <version>0.6.3</version>
</dependency>
```

Old way:

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

Where GitHub_username it is your username in GitHub. Password - your GitHub personal access token. To get such a token,
you need to go to GitHub settings, then go to Developer settings, where in the Personal Access Token section click
Generate new token. In the generation menu, you only need to select the read:packages checkbox. The generated token
needs to be inserted into the password field in the above example.

More information about PAT

- [github docs](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)

Dependency

```xml
<dependency>
    <groupId>org.ostis</groupId>
    <artifactId>jmantic</artifactId>
    <version>0.6.1</version>
</dependency>
```

****

## Ostis configuration

To use JMantic, you need ostis version 0.6.0. You can run ostis any way you want. For example, you can run ostis in the
docker with the following command (you may have to run these commands with sudo):

```shell
docker run -it -p 8000:8000 -p 8090:8090 ostis/ostis:0.6.0 sh ostis --all
```

If you do not need to build the base, you can speed up the startup with the following command

```shell
docker run -it -p 8000:8000 -p 8090:8090 ostis/ostis:0.6.0 sh ostis --sctp --web
```


More information about dockerized ostis - [GitHub repository](https://github.com/ostis-apps/dockerized-ostis)

****

## Conventions 

### JMantic versioning 
In this project, the semantic version is used but with a couple of improvisations.

Version has the format: x.y.z 

Where:
1) x - is a major version. A big difference can be between major versions. Only domain and purpose must be the same in different major versions. 
2) y - is a minor version. The public api and implementation can be changed between minor versions, but the core interfaces must not be removed.
3) z - versioning for bug fixing and small features adding. Public api cannot be changed. 

### Sc-elements naming 

ToDo

[//]: # (Each element when triplet creation should be on new line )

****

## Used libraries

* [Jakarta.websocket-api](https://github.com/jakartaee/websocket)
* [Tyrus-standalone-client](https://github.com/eclipse-ee4j/tyrus)
* [JUnit5](https://github.com/junit-team/junit5)
* [Jackson-databind](https://github.com/FasterXML/jackson-databind)

## Related technologies

* [WebSocket](https://sookocheff.com/post/networking/how-do-websockets-work/)
* [json](https://www.w3schools.com/whatis/whatis_json.asp)
* [sc-machine](http://ostis-dev.github.io/sc-machine/)
* [sc-machine-WebSocket-protocol](http://ostis-dev.github.io/sc-machine/http/websocket/)
