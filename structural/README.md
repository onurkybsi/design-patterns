# Structural Design Patterns

## Adapter(Wrapper)

> Adapter lets classes work together that couldn' t otherwise because of incompatible interfaces. [1]

I will give the example of an adapter and socket that you can see in almost most sources, which we think makes this design pattern easy to understand. When you travel EU from US, you might not be able to charge your telephone because of the power plug.
In US; the power plug standard is different than EU so, you'll need an **adapter**!

Example;

We have .pdf files data from a 3rd party integration, and the other 3rd integration requires JSON.

### Motivation

Sometimes the new interfaces we want to integrate may not be compatible with the interfaces we already have. For example, let's say we download our users' identification data in PDF file format from a 3rd party system. In short, in our currently running system, user identification data are kept as PDFs and used as PDFs everywhere in our system. And suppose we need to develop a new integration into our existing system due to a new feature. The 3rd party system expect our users' identification data in JSON format. What are we gonna do ? Are we going to change the component responsible for managing user identification data ? On the one hand, of course, we can expand, but on the other hand, we give him something for which he is not really responsible(can we say it would be against [SOLID](https://en.wikipedia.org/wiki/SOLID)'s S ?). 

### Solution

Let's develop a complete separate component which is only responsible to adapt our existing interface to the new one!

### Resource

[1] Erich Gamma, Richard Helm, Ralp Johnson, John Vlissides. 1997. Design Patterns. 1st Edition.