package org.kybprototyping.adapter.existing_api;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

// Implementation of the existing interface, we don't want to change that!
public class IdentificationDataProviderServiceImpl implements IdentificationDataProviderService {

  @Override
  public File getIdentificationData(UUID userId) {
    try {
      URL identificationDataUrl = getClass().getClassLoader().getResource(String.format("%s.pdf", userId));
      // Identification document is returned as a PDF file which the new 3rd party API doesn't accept!
      return new File(identificationDataUrl.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
      throw new RuntimeException("File couldn't be received!");
    }
  }

  
}
