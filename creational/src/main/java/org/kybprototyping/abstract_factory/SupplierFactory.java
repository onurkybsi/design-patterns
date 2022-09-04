package org.kybprototyping.abstract_factory;

public interface SupplierFactory {
  CellPhone produceCellPhone();

  ChargingCable produceChargingCable();
}
