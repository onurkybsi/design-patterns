package org.kybprototyping.adapter;

import java.util.UUID;

import org.kybprototyping.adapter.existing_api.IdentificationDataProviderService;

// The service which is going to implement the new feature with the new 3rd party integration
public class AdapteeService {
  private final IdentificationDataProviderService existingService;
  private final NewThirdPartyAdapter newThirdPartyAdapter;

  public AdapteeService(IdentificationDataProviderService existingService, NewThirdPartyAdapter newThirdPartyAdapter) {
    this.existingService = existingService;
    this.newThirdPartyAdapter = newThirdPartyAdapter;
  }

  public void processNeedsToBeImplementedWithNewFeature(UUID customerId, Object[] otherParametersForTheNewFeature) {
    // implement business rules ...

    // That's the reason why we need adapter pattern!
    // Adapting the new 3rd party API to the current system is not the responsibility of this component!
    // So, ..
    newThirdPartyAdapter.onboardCustomerOnNewThirdParty(existingService.getIdentificationData(customerId));
    // implement business rules ...
  }
}
