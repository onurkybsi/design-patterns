package org.kybprototyping.abstract_factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SupplierTest {
  @Test
  void getFactory_Returns_Implementation_Of_ManufacturerAbstractFactory_Which_Creates_Related_Objects() {
    // arrange
    Supplier supplier = new Supplier();

    // act
    ManufacturerAbstractFactory appleFactory = supplier.getFactory(Brand.APPLE);
    ManufacturerAbstractFactory samsungFactory = supplier.getFactory(Brand.SAMSUNG);

    // assert
    assertEquals(appleFactory.getCellPhone().getBrand(), appleFactory.getChargingCable().getBrand());
    assertEquals(samsungFactory.getCellPhone().getBrand(), samsungFactory.getChargingCable().getBrand());
  }
}
