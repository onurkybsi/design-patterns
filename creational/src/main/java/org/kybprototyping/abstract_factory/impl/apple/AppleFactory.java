package org.kybprototyping.abstract_factory.impl.apple;

import org.kybprototyping.abstract_factory.CellPhone;
import org.kybprototyping.abstract_factory.ChargingCable;
import org.kybprototyping.abstract_factory.SupplierFactory;

import java.util.logging.Logger;

public class AppleFactory implements SupplierFactory {
  private static Logger logger = Logger.getLogger("AppleFactory");

  private final AppleConfiguration configuration;

  public AppleFactory(AppleConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public CellPhone getCellPhone() {
    return new Iphone(configuration.getIphoneConfig().getPrice());
  }

  @Override
  public ChargingCable getChargingCable() {
    return new AppleChargingCable(configuration.getAppleChargingCableConfig().getPrice());
  }
}
