package org.kybprototyping.adapter;

import java.io.File;
import java.util.UUID;

public class IdentificationDataProviderServiceImpl implements IdentificationDataProviderService {

  @Override
  public File getIdentificationData(UUID userId) {
    // Let say that the identification document is returned as a PDF file
    return null;
  }

  
}
