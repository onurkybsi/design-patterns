package org.kybprototyping.adapter;

import java.util.UUID;

import org.kybprototyping.adapter.existing_api.IdentificationDataProviderService;

// The service which is going to take the responsibility of the new feature which needs the new 3rd party integration
public class AdapteeBusinessService {
  private final IdentificationDataProviderService existingService;
  private final NewThirdPartyAdapter newThirdPartyAdapter;

  public AdapteeBusinessService(IdentificationDataProviderService existingService, NewThirdPartyAdapter newThirdPartyAdapter) {
    this.existingService = existingService;
    this.newThirdPartyAdapter = newThirdPartyAdapter;
  }

  public void processNeedsToBeImplementedWithNewFeature(UUID customerId) {
    // Implementation of the business rules regarding the new feature...
    var identificationData = existingService.getIdentificationData(customerId);

    // That's the reason why we need the adapter pattern! We don't want this service to get complicated, 
    // we only want to need some services from some a 3rd party to implement our business rules and
    // we don't want to change the logic(I mean existing API) in our system.
    // Thanks to the adapter, we can focus the business rules which the feature requires and the adapter can take care of the other stuff!
    newThirdPartyAdapter.onboardCustomerOnNewThirdParty(identificationData);

    // Continue to the implementation of the business rules
  }
}
