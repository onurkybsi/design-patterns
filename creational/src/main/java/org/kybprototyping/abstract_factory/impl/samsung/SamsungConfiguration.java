package org.kybprototyping.abstract_factory.impl.samsung;

public class SamsungConfiguration {
  private static class SamsungConfigBase {
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

  public static class GalaxyConfig extends SamsungConfigBase {
  }

  public static class SamsungChargingCableConfig extends SamsungConfigBase {
  }

  private final GalaxyConfig galaxyConfig;

  private final SamsungChargingCableConfig samsungChargingCableConfig;

  public SamsungConfiguration(GalaxyConfig galaxyConfig, SamsungChargingCableConfig samsungChargingCableConfig) {
    this.galaxyConfig = galaxyConfig;
    this.samsungChargingCableConfig = samsungChargingCableConfig;
  }

  public GalaxyConfig getGalaxyConfig() {
    return galaxyConfig;
  }

  public SamsungChargingCableConfig getSamsungChargingCableConfig() {
    return samsungChargingCableConfig;
  }
}
