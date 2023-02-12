# Behavioral Design Patterns

## Observer

### Overview

Observer design pattern proposes a way for the communication between several components(**observers**) through a common channel. In this pattern, we basically have a **subject** component which notifies its observers when the state of the subject is changed. The state might be changed by different sources like one of the observers or an external request.

### When

Basically, we should think about to implement _Observer design pattern_ when we want to establish a one-to-many relationship between the components which might interest the state of a common subject.

### Why

The best part of this pattern is probably _to have unlimited numbers of loosely coupled, reusable components which needs consistency_ between them. The subject component doesn't know anything about its observers except that they support an interface which can be used to notify them. So the subject can be extended with new obsevers. On the other hand, the observers don't know anything about each other. So they can be reused independently.

### In Action

- The [example](./src/main/java/org/kybprototyping/observer) I developed. [_Javadoc_](./target/site/apidocs/index.html) is available and I also left some comments for each statement to explain the example clearly. The example is inspired by **event-driven design**, one of today's popular system design patterns based on _Observer design pattern_. You can check the [link](https://en.wikipedia.org/wiki/Event-driven_architecture) for more details.

## Memento

### Overview

### When

### Why

### In Action
