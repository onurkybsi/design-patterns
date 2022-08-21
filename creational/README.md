# Creational Design Patterns

## Singleton

In singleton design pattern, *a class has only one instance* for the whole application scope. That instance is accessible for the whole application scope via a global access point.

### Motivation

Sometimes you may want to design your classes so that they can only be instantiated once. If a class has no state, you should want. For example, let's think about a class like below:

```java
class SomeService {
  String getSomeStringInfo() {
    // ...
  }

  Integer getSomeIntegerInfo() {
    // ...
  }
}
```

This class has only methods and no any state so that this class can be used by multiple clients at the same time(thread-safe) without any drawback. Why would we allow clients of this class to be able to instantiate multiple objects from this class ? Ultimately, this will cause unnecessary memory usage. In these cases, we want to prevent having multiple instances of the class. At this point, singleton design pattern comes in.

### Solution

1. To have exactly one instance of the class, we need to control construction of the class **setting its constructor as private**.
2. To provide a global access point, we need **a static factory method which controls singularity**.