package org.kybprototyping.abstract_factory.impl.samsung;

import org.kybprototyping.abstract_factory.CellPhone;

class Galaxy implements CellPhone {
  private final Integer price;

  Galaxy(Integer configuredPrice) {
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
