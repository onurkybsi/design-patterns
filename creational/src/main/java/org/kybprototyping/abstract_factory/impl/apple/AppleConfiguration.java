package org.kybprototyping.abstract_factory.impl.apple;

public class AppleConfiguration {
  private static class AppleConfigBase {
    private Integer stock;
    private Integer price;

    public void setStock(Integer stock) {
      this.stock = stock;
    }

    public Integer getStock() {
      return stock;
    }

    public void setPrice(Integer price) {
      this.price = price;
    }

    public Integer getPrice() {
      return price;
    }
  }

  public static class IphoneConfig extends AppleConfigBase {
  }

  public static class AppleChargingCableConfig extends AppleConfigBase {
  }

  private final IphoneConfig iphoneConfig;

  private final AppleChargingCable appleChargingCableConfig;

  public AppleConfiguration(IphoneConfig iphoneConfig, AppleChargingCable appleChargingCableConfig) {
    this.iphoneConfig = iphoneConfig;
    this.appleChargingCableConfig = appleChargingCableConfig;
  }

  public IphoneConfig getIphoneConfig() {
    return iphoneConfig;
  }

  public AppleChargingCable getAppleChargingCableConfig() {
    return appleChargingCableConfig;
  }
}
