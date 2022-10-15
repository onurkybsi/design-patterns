package org.kybprototyping.abstract_factory.impl.samsung;

import org.kybprototyping.abstract_factory.CellPhone;
import org.kybprototyping.abstract_factory.ChargingCable;
import org.kybprototyping.abstract_factory.ManufacturerAbstractFactory;

public class SamsungFactory implements ManufacturerAbstractFactory {
  @Override
  public CellPhone getCellPhone() {
    return new Galaxy();
  }

  @Override
  public ChargingCable getChargingCable() {
    return new SamsungChargingCable();
  }
}
