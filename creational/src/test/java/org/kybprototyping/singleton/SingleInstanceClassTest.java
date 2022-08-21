package org.kybprototyping.singleton;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class SingleInstanceClassTest {
  @Test
  void getInstance_Returns_SameInstance_ForEveryInovocation() throws InterruptedException {
    // arrange
    List<SingleInstanceClass> receivedInstancesFromConcurrentCalls = new ArrayList<>();
    Callable<Void> concurrentSingletonInstanceCall = () -> {
      SingleInstanceClass receivedInstance = SingleInstanceClass.getInstance();
      receivedInstancesFromConcurrentCalls.add(receivedInstance);
      return null;
    };
    List<Callable<Void>> concurrentSingletonInstanceCalls = new ArrayList<>();
    IntStream.range(0, 3)
        .forEach(i -> concurrentSingletonInstanceCalls.add(concurrentSingletonInstanceCall));
    // act
    Executors.newFixedThreadPool(10).invokeAll(concurrentSingletonInstanceCalls, Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    // assert
    int expectedHashCodeForAllInstances = SingleInstanceClass.getInstance().hashCode();
    assertTrue(receivedInstancesFromConcurrentCalls.stream()
        .allMatch(instance -> instance.hashCode() == expectedHashCodeForAllInstances));
  }
}
