package org.kybprototyping.abstract_factory.impl.apple;

import org.kybprototyping.abstract_factory.CellPhone;

class Iphone implements CellPhone {
  private final Integer price;

  Iphone(Integer configuredPrice) {
    this.price = configuredPrice;
  }

  @Override
  public String getBrand() {
    return "Apple";
  }

  @Override
  public Integer getPrice() {
    return price;
  }

}
