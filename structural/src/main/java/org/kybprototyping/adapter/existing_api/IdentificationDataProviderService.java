package org.kybprototyping.adapter.existing_api;

import java.io.File;
import java.util.UUID;

// Existing interface
public interface IdentificationDataProviderService {
  // Returns something that the new 3rd part doesn't expect
  File getIdentificationData(UUID userId);
}
