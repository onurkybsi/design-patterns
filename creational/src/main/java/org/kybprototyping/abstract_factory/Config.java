package org.kybprototyping.abstract_factory;

import org.kybprototyping.abstract_factory.impl.apple.AppleConfiguration;
import org.kybprototyping.abstract_factory.impl.samsung.SamsungConfiguration;

class Config {
  static class ElectronicsStoreConfig {
    private String preferredBrand;

    public String getPreferredBrand() {
      return preferredBrand;
    }

    public void setPreferredBrand(String preferredBrand) {
      this.preferredBrand = preferredBrand;
    }
  }

  private ElectronicsStoreConfig electronicsStoreConfig;
  private AppleConfiguration appleConfig;
  private SamsungConfiguration samsungConfig;

  ElectronicsStoreConfig getElectronicsStoreConfig() {
    return electronicsStoreConfig;
  }

  void setElectronicsStore(ElectronicsStoreConfig electronicsStoreConfig) {
    this.electronicsStoreConfig = electronicsStoreConfig;
  }

  AppleConfiguration getAppleConfig() {
    return appleConfig;
  }

  void setApple(AppleConfiguration appleConfig) {
    this.appleConfig = appleConfig;
  }

  SamsungConfiguration getSamsung() {
    return samsungConfig;
  }

  void setSamsung(SamsungConfiguration samsungConfig) {
    this.samsungConfig = samsungConfig;
  }
}
