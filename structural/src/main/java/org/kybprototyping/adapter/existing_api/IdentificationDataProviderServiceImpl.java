package org.kybprototyping.adapter.existing_api;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

public class IdentificationDataProviderServiceImpl implements IdentificationDataProviderService {

  @Override
  public File getIdentificationData(UUID userId) {
    try {
      URL identificationDataUrl = getClass().getClassLoader().getResource(String.format("%s.pdf", userId));
      // Identification document is returned as a PDF file
      return new File(identificationDataUrl.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
      throw new RuntimeException("File couldn't be received!");
    }
  }

  
}
