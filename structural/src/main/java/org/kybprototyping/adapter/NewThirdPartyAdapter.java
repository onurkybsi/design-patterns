package org.kybprototyping.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

// The adapter! The new 3rd party integration is based on a REST API communication and the API expects JSON body as the identification data 
public class NewThirdPartyAdapter {
  private static final String NEW_THIRD_PARTY_API_BASE_URL = "http://localhost:8080/api";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  // Existing system uses File as the type of identification data 
  public void onboardCustomerOnNewThirdParty(File identificationData) {
    List<String> lines = new ArrayList<>();
    try (var reader = new BufferedReader(new FileReader(identificationData))) {
      String line = reader.readLine();
      while (line != null) {
        lines.add(line);
        line = reader.readLine();
      }
      String jsonStr = convertLinesToJsonStr(lines);
      sendToNewThirdPartyApi(jsonStr);
    } catch (IOException e) {
      throw new RuntimeException("File couldn't be read!");
    }
  }

  private String convertLinesToJsonStr(List<String> lines) {
    ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
    for (String line : lines) {
      String[] keyValue = line.trim().split("=");
      objectNode.put(keyValue[0], keyValue[1]);
    }
    return objectNode.toString();
  }

  private void sendToNewThirdPartyApi(String jsonStr) {
    try {
      HttpClient.newHttpClient().send(HttpRequest.newBuilder()
        .uri(URI.create(NEW_THIRD_PARTY_API_BASE_URL + "/customer"))
        .POST(BodyPublishers.ofString(jsonStr))
        .header("Content-Type", "application/json")
        .build(), BodyHandlers.discarding());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Onboard unsucessful!");
    }
  }
}