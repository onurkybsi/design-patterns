# Creational Design Patterns

## Singleton

In singleton design pattern, *the program has exactly one instance for the singleton type* for the whole application scope. That instance is accessible from the whole application scope through a global access point.

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

This class has only methods and no state so only one instance which is initiated from this class would be enough for concurrent multiple clients without any drawback. So, why would we allow clients of this class to be able to instantiate multiple objects from this class ? Ultimately, this will cause unnecessary memory usage. In these cases, we want to prevent having multiple instances of classes. At this point, singleton design pattern comes in.

### Solution

1. To have exactly one instance of the class, we need to control construction of the class **setting its constructor as private**.
2. To provide a global access point, we need **a static factory method which controls singularity**.

[Check my implementation!](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/singleton/SingleInstanceClass.java) And [see](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/test/java/org/kybprototyping/singleton/SingleInstanceClassTest.java#L16) that concurrent clients of the singleton class don't run into any issues when they get it.