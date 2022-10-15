package org.kybprototyping.abstract_factory.impl.samsung;

import org.kybprototyping.abstract_factory.ChargingCable;

class SamsungChargingCable implements ChargingCable {
  @Override
  public String getBrand() {
    return "Samsung";
  }

  @Override
  public Integer getPrice() {
    return 40;
  }
}
