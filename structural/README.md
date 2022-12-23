# Structural Design Patterns

## Adapter(Wrapper)

> Adapter lets classes work together that couldn't otherwise because of incompatible interfaces. [1]

I will give the example of an adapter and socket that you can see in almost most sources, which I think makes this design pattern easy to understand. When you travel EU from US, you might not be able to charge your telephone because of the power plug.
In US; the power plug standard is different than EU so, you'll need an **adapter**!
![adapter](https://m.media-amazon.com/images/W/WEBP_402378-T2/images/I/31pAm6p4fDL._AC_.jpg)

### Motivation

Sometimes the new interfaces that we want to include our system may not be compatible with the interfaces we already have. For example, let's say we download our users' identification data in PDF file format from a 3rd party system. In short, in our currently running system, user identification data are kept as PDFs and used as PDFs everywhere in the system. And suppose we need to develop a new feature which requires a new 3rd party integration. Let say that the 3rd party system expect our users' identification data in JSON format. What are we gonna do ? Are we going to change the component responsible for managing user identification data ? Or the component that we're gonna implement the business rules regarding the feature ? On the one hand, of course, we can expand, but on the other hand, we give them something for which they're not really their responsibility(can we say it would be against [SOLID](https://en.wikipedia.org/wiki/SOLID)'s S ?). 

### Solution

Let's develop a complete separate component which is only responsible to adapt our existing interface to the new one! Check [the implementation](https://github.com/onurkybsi/design-patterns/tree/main/structural/src/main/java/org/kybprototyping/adapter) and [the test](https://github.com/onurkybsi/design-patterns/blob/main/structural/src/test/java/org/kybprototyping/adapter/NewThirdPartyAdapterTest.java#L17), everything explained in the comments! 

### Resource

[1] Erich Gamma, Richard Helm, Ralp Johnson, John Vlissides. 1997. Design Patterns. 1st Edition.