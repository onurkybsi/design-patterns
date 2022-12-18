# Structural Design Patterns

## Adapter(Wrapper)

- Convert the interface of a class into another interface clients expect. Adapter lets
classes work together that couldn' t otherwise because of incompatible interfaces.
- Sometimes a toolkit class that's designed for reuse isn't reusable only because its
interface doesn't match the domain-specific interface an application requires.
- How can existing and unrelated classes work in an application that expects classes with a different and incompatible interface?
- Often the adapter is responsible for functionali ty the adapted class doesn't pro
vide.

- Use the Adapter pattern when
  - you want to use an existing class, and its interface does not match the one
  you need.
  - you want to create a reusable class that cooperates with unrelated orunforeseen
  classes, that is, classes that don't necessarily have compatible interfaces.
  - (object adapter only) you need to use several existing subclasses, but it's unpractical
  to adapt their interfa ce by subclassing every one. An obje ct adapter
  can adapt the interface of its parent class.

- Clients call operations on an Adapter instance. In turn, the adapter call s
Adaptec operations that carry out the request.

Example;

We have .sdf files data, the 3rd integration requires JSON.