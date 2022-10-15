package org.kybprototyping.abstract_factory.impl.apple;

import org.kybprototyping.abstract_factory.ChargingCable;

class AppleChargingCable implements ChargingCable {
  @Override
  public String getBrand() {
    return "Apple";
  }

  @Override
  public Integer getPrice() {
    return 50;
  }
}
