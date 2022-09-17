package org.kybprototyping.abstract_factory;

public class ElectronicsStore {
  private SupplierFactory supplierFactory;

  public ElectronicsStore(SupplierFactory supplierFactory) {
    this.supplierFactory = supplierFactory;
  }

  private void supplyProducts() {

  }
}
