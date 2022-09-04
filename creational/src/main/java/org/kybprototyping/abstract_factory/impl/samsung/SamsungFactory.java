package org.kybprototyping.abstract_factory.impl.samsung;

import org.kybprototyping.abstract_factory.CellPhone;
import org.kybprototyping.abstract_factory.ChargingCable;
import org.kybprototyping.abstract_factory.SupplierFactory;

class SamsungFactory implements SupplierFactory {
  private final SamsungConfiguration configuration;

  public SamsungFactory(SamsungConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public CellPhone produceCellPhone() {
    return new Galaxy(configuration.getGalaxyConfig().getPrice());
  }

  @Override
  public ChargingCable produceChargingCable() {
    return new SamsungChargingCable(configuration.getSamsungChargingCableConfig().getPrice());
  }

}