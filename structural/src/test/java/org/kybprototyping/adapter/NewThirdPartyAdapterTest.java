package org.kybprototyping.adapter;


import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.kybprototyping.adapter.existing_api.IdentificationDataProviderService;
import org.kybprototyping.adapter.existing_api.IdentificationDataProviderServiceImpl;
import org.mockserver.mock.Expectation;
import org.mockserver.model.ExpectationId;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;

import com.fasterxml.jackson.databind.ObjectMapper;

class NewThirdPartyAdapterTest extends MockNewThirdPartyRestApiServer {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  
  @Test
  void onboardCustomerOnNewThirdParty_Should_Convert_Given_File_To_Proper_Identification_Data_Json_String_And_Passes_To_New_Third_Party_Rest_Api() {
    // given
    Expectation[] expectations = mockServer
        .when(HttpRequest
            .request()
            .withPath("/api/customer")
            .withHeader("Content-Type", "application/json")
            .withBody("{\"name\":\"Onur\",\"surname\":\"Kayabasi\"}"))
        .respond(HttpResponse
            .response()
            .withStatusCode(200));
    IdentificationDataProviderService identificationDataProviderService = new IdentificationDataProviderServiceImpl();
    // when
    new NewThirdPartyAdapter().onboardCustomerOnNewThirdParty(identificationDataProviderService.getIdentificationData(UUID.fromString("62a3fee1-c006-4512-8296-752ff18b8d10")));

    // then
    mockServer.verify(ExpectationId.expectationId(expectations[0].getId()), VerificationTimes.exactly(1));
  }
}
