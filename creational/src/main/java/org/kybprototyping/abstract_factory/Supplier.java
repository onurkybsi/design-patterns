package org.kybprototyping.abstract_factory;

import org.kybprototyping.abstract_factory.impl.apple.AppleFactory;
import org.kybprototyping.abstract_factory.impl.samsung.SamsungFactory;

public class Supplier {
  private static final String UNSUPPORTED_BRANCD_EXCEPTION_MESSAGE = "brand can only be \"Apple\" or \"Samsung\"!";

  private AppleFactory appleFactory = new AppleFactory();
  private SamsungFactory samsungFactory = new SamsungFactory();

  public CellPhone getCellPhone(Brand brand) {
    return switch (brand) {
      case APPLE -> appleFactory.getCellPhone();
      case SAMSUNG -> samsungFactory.getCellPhone();
      default -> throw new IllegalArgumentException(UNSUPPORTED_BRANCD_EXCEPTION_MESSAGE);
    };
  }

  public ChargingCable getChargingCable(Brand brand) {
    return switch (brand) {
      case APPLE -> appleFactory.getChargingCable();
      case SAMSUNG -> samsungFactory.getChargingCable();
      default -> throw new IllegalArgumentException(UNSUPPORTED_BRANCD_EXCEPTION_MESSAGE);
    };
  }

  public ManufacturerAbstractFactory getFactory(Brand brand) {
    return switch (brand) {
      case APPLE -> appleFactory;
      case SAMSUNG -> samsungFactory;
      default -> throw new IllegalArgumentException(UNSUPPORTED_BRANCD_EXCEPTION_MESSAGE);
    };
  }
}
