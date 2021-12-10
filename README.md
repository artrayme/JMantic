![](docs/logo/project_logo.png)

# JMantic Project

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

## Used libraries

* Java-WebSocket https://github.com/TooTallNate/Java-WebSocket
* JUnit5 https://github.com/junit-team/junit5
* Jackson-databind https://github.com/FasterXML/jackson-databind

## Related technologies

* WebSocket https://sookocheff.com/post/networking/how-do-websockets-work/
* json https://www.w3schools.com/whatis/whatis_json.asp
* sc-machine http://ostis-dev.github.io/sc-machine/

