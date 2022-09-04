package org.kybprototyping.abstract_factory.impl.samsung;

import org.kybprototyping.abstract_factory.ChargingCable;

class SamsungChargingCable implements ChargingCable {
  private static final String TYPE = "Lightning";

  private final Integer price;

  SamsungChargingCable(Integer configuredPrice) {
    this.price = configuredPrice;
  }

  @Override
  public String getBrand() {
    return Constant.BRAND;
  }

  @Override
  public Integer getPrice() {
    return price;
  }

  @Override
  public String getType() {
    return TYPE;
  }

}
