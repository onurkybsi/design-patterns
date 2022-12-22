package org.kybprototyping.adapter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.integration.ClientAndServer;

class MockNewThirdPartyRestApiServer {
  protected static ClientAndServer mockServer;

  @BeforeAll
  public static void startMockServer() {
    mockServer = ClientAndServer.startClientAndServer(8080);
  }

  @BeforeEach
  void refresh() {
    mockServer.reset();
  }

  @AfterAll
  public static void stopMockServer() {
    mockServer.stop();
  }
}
