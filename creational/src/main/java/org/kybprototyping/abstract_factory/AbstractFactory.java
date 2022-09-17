package org.kybprototyping.abstract_factory;

import org.kybprototyping.Main;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@SuppressWarnings("java:S112")
public class AbstractFactory {
  private static Logger logger = Logger.getLogger("AbstractFactory");
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final Config appConfig;

  static {
    try {
      InputStream problemArgsInputStream = Main.class.getClassLoader()
          .getResourceAsStream("problem-args.json");
      appConfig = objectMapper.readValue(problemArgsInputStream, Config.class);
    } catch (IOException e) {
      throw new RuntimeException("Configuration could not be constructed!", e);
    }
  }

  public static void describeScenario() {
    SupplierFactory supplierFactory = initiateSupplierFactoryByConfig();
    ElectronicsStore anElectronicsStore = new ElectronicsStore();

  }

  private static SupplierFactory initiateSupplierFactoryByConfig() {
    if (appConfig.getElectronicsStoreConfig().getPreferredBrand() == "Apple") {
      return new AppleFactory(appConfig.getAppleConfig());
    }
    return null;
  }
}
