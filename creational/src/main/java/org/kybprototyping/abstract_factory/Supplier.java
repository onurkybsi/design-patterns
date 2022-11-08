package org.kybprototyping.abstract_factory;

import org.kybprototyping.abstract_factory.impl.apple.AppleFactory;
import org.kybprototyping.abstract_factory.impl.samsung.SamsungFactory;

// Client needs only to communicate with factory provider to access manufacturer products
public class Supplier {
  private static final String UNSUPPORTED_BRANCD_EXCEPTION_MESSAGE = "brand can only be \"Apple\" or \"Samsung\"!";

  private AppleFactory appleFactory = new AppleFactory();
  private SamsungFactory samsungFactory = new SamsungFactory();

  // Client can choose itself the created instance through some configuration
  // parameters(brand in this case)
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

  // Client also can get the factory itself by some configurations.
  // So, it can construct the objects from the same family.
  // For example: Iphone & AppleChargingCable
  public ManufacturerAbstractFactory getFactory(Brand brand) {
    return switch (brand) {
      case APPLE -> appleFactory;
      case SAMSUNG -> samsungFactory;
      default -> throw new IllegalArgumentException(UNSUPPORTED_BRANCD_EXCEPTION_MESSAGE);
    };
  }
}
