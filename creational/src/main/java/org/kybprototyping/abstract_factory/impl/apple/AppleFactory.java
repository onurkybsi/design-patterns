package org.kybprototyping.abstract_factory.impl.apple;

import org.kybprototyping.abstract_factory.CellPhone;
import org.kybprototyping.abstract_factory.ChargingCable;
import org.kybprototyping.abstract_factory.SupplierFactory;

class AppleFactory implements SupplierFactory {
  private final AppleConfiguration configuration;

  public AppleFactory(AppleConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public CellPhone produceCellPhone() {
    return new Iphone(configuration.getIphoneConfig().getPrice());
  }

  @Override
  public ChargingCable produceChargingCable() {
    return new AppleChargingCable(configuration.getAppleChargingCableConfig().getPrice());
  }

}
