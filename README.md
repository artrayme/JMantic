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
  list of usage examples. The public api uses only basic abstractions.All classes and methods are documented.There is a
  wide list of usage examples.
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

## Main project concepts

The entire implementation is based on the ScMemory interface, which describes the general contract between the java code
and the sc-machine. This interface contains the methods that the sc-machine must have in order to work.

The sc-machine operates with sc-elements. The project has a hierarchy of interfaces that describes the types of
sc-elements and the relationships between sc-elements. At the top of this hierarchy is the sc-element itself, which is
represented by the scElement interface. There are 3 types of scElement:

* ScNode
* ScEdge
* ScLink

However, working directly with ScMemory can be not very convenient and efficient. The solution to this problem are
ScContexts. ScContext - a wrapper over ScMemory, which can provide a more convenient or faster interface for working
with ScMemory. At this moment, there is no unified ScContext interface in the project, but there are some
implementations of this idea.

## How to use

First, you have to choose the ScMemory implementation you want to use. The simplest is the SyncScMemory implementation,
which is a websocket client that is not designed to work in a multithreading environment.

```java
ScMemory memory = new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
```

After creating a ScMemory, you can use it immediately, but you can wrap it in a ScContext. For this example we will take
UncheckedScContext because it helps us not to handle exceptions (this is a very bad practice, never do that. This
implementation is just an example)

```java
UncheckedScContext scContext = new UncheckedScContext(memory);
```

Now that we have the ScContext, we can do some actions on the ScMemory. For example, creating a node

```java
ScNode node=scContext.createNode(NodeType.NODE);
```

Or creating an edge between the nodes

```java
ScNode source=scContext.createNode(NodeType.NODE);
ScNode target=scContext.createNode(NodeType.NODE);
ScEdge edge=scContext.createEdge(EdgeType.ACCESS,source,target);
```

****

## Used libraries

* Java-WebSocket https://github.com/TooTallNate/Java-WebSocket
* JUnit5 https://github.com/junit-team/junit5
* Jackson-databind https://github.com/FasterXML/jackson-databind

## Related technologies

* WebSocket https://sookocheff.com/post/networking/how-do-websockets-work/
* json https://www.w3schools.com/whatis/whatis_json.asp
* sc-machine http://ostis-dev.github.io/sc-machine/

