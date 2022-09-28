package it.dantonio.bankingapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope("singleton")
public class ClientMethod {

  private final RestTemplate rest;
  private final HttpHeaders headers;

  Logger logger = Logger.getLogger(ClientMethod.class.getName());


  public ClientMethod(@Value("${auth-schema}") String authSchema,
      @Value("${api-key}") String apiKey) {
    this.rest = new RestTemplate();
    this.headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Auth-Schema", authSchema);
    headers.add("Api-Key", apiKey);
  }

  public String callHttpMethod(String endpoint, String json, HttpMethod httpMethod) {
    try {
      HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
      ResponseEntity<String> responseEntity = rest.exchange(endpoint, httpMethod, requestEntity,
          String.class);
      if (responseEntity.getStatusCode() == HttpStatus.OK) {
        return extractPayload(responseEntity.getBody());
      }
    } catch (Exception e){
      logger.log(Level.SEVERE, "Exception calling " + endpoint);
      logger.log(Level.SEVERE, e.getMessage());
    }
    return null;
  }

  private String extractPayload(String bodyResponse) throws JsonProcessingException {
    JsonNode jsonNode = new ObjectMapper().readTree(bodyResponse).get("payload");
    return jsonNode.get("list") != null ? jsonNode.get("list").toString() : jsonNode.toString();
  }
}
