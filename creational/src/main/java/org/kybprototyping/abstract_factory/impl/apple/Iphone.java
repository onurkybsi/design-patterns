package org.kybprototyping.abstract_factory.impl.apple;

import org.kybprototyping.abstract_factory.CellPhone;

class Iphone implements CellPhone {
  @Override
  public String getBrand() {
    return "Apple";
  }

  @Override
  public Integer getPrice() {
    return 900;
  }
}
