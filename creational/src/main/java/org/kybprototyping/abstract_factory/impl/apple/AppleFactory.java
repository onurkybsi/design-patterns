package org.kybprototyping.abstract_factory.impl.apple;

import org.kybprototyping.abstract_factory.CellPhone;
import org.kybprototyping.abstract_factory.ChargingCable;
import org.kybprototyping.abstract_factory.ManufacturerAbstractFactory;

public class AppleFactory implements ManufacturerAbstractFactory {
  @Override
  public CellPhone getCellPhone() {
    return new Iphone();
  }

  @Override
  public ChargingCable getChargingCable() {
    return new AppleChargingCable();
  }
}
