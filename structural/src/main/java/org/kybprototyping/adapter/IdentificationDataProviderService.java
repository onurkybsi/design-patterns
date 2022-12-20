package org.kybprototyping.adapter;

import java.io.File;
import java.util.UUID;

public interface IdentificationDataProviderService {
  File getIdentificationData(UUID userId);
}
