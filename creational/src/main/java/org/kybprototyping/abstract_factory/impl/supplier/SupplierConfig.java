package org.kybprototyping.abstract_factory.impl.supplier;

import org.kybprototyping.abstract_factory.impl.apple.AppleConfiguration;
import org.kybprototyping.abstract_factory.impl.samsung.SamsungConfiguration;

class SupplierConfig {
  private final AppleConfiguration appleConfiguration;
  private final SamsungConfiguration samsungConfiguration;

  SupplierConfig(AppleConfiguration appleConfiguration, SamsungConfiguration samsungConfiguration) {
    this.appleConfiguration = appleConfiguration;
    this.samsungConfiguration = samsungConfiguration;
  }

  AppleConfiguration getAppleConfiguration() {
    return appleConfiguration;
  }

  SamsungConfiguration getSamsungConfiguration() {
    return samsungConfiguration;
  }
}
