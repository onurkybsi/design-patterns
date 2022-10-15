package org.kybprototyping.abstract_factory.impl.samsung;

import org.kybprototyping.abstract_factory.CellPhone;

class Galaxy implements CellPhone {
  @Override
  public String getBrand() {
    return "Samsung";
  }

  @Override
  public Integer getPrice() {
    return 800;
  }
}
