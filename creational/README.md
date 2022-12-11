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

[Check my implementation!](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/singleton/SingleInstanceClass.java) And [see](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/test/java/org/kybprototyping/singleton/SingleInstanceClassTest.java#L16) that concurrent clients of the singleton class don't run into any issues when they get the single instance.

## Abstract Factory

Abstract factory design pattern propose a solution to *create related or dependent objects together* while hiding the implementation details.

### Motivation

Let say that you're going to integrate your system to a 3rd party(external) system by applying your business rules. The 3rd party system has a REST API for its partner like you and you're going to use it for that integration. For that kind of integration you would probably use two component in your system like: `SomeServiceXImpl`(implements `SomeService`) and `3rdPartyClientXImpl`(impelements `3rdPartyClient`). Obviously, these implementations are related and dependent to each other. You don't want to create `SomeServiceXImpl` with another `3rdPartyClient` implementation and `3rdPartyClientXImpl` withouth existence of `SomeServiceXImpl`(becasue `3rdPartyClientXImpl` is only used by `SomeServiceXImpl`).

Probably, you're going to have new 3rd parties for the same business cases(I'm sure that you are :smile:) and you will want to bundle these related objects together. So, how ? :slightly_smiling_face:

### Solution

In the [example](https://github.com/onurkybsi/design-patterns/tree/main/creational/src/main/java/org/kybprototyping/abstract_factory) I've developed, I've used the dependency between a [`CellPhone`](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/abstract_factory/CellPhone.java#L3) and its [`ChargingCable`](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/abstract_factory/ChargingCable.java#L3). The [`Supplier`](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/abstract_factory/Supplier.java#L7) needs to bundle these objects together because a cell phone X cannot be charged with the charging cable Y.

1. Define the abstract factory &#8594; [`ManufacturerAbstractFactory`](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/abstract_factory/ManufacturerAbstractFactory.java#L3)
2. Implement the factory for the different related object families(`CellPhone`, `ChargingCable`) &#8594; [`AppleFactory`](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/abstract_factory/impl/apple/AppleFactory.java#L7) && [`SamsungFactory`](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/main/java/org/kybprototyping/abstract_factory/impl/samsung/SamsungFactory.java#L7)
3. The created factories by the given configuration(`Brand` choice) will create the related objects together, inconsistency won't be happen and the client of the `Supplier` will not know anything about the implementation detail(check the [test](https://github.com/onurkybsi/design-patterns/blob/main/creational/src/test/java/org/kybprototyping/abstract_factory/abstract_factory/SupplierTest.java#L9) for the scenario).