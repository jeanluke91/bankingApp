package it.dantonio.bankingapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope("singleton")
public class ClientMethod {
    private RestTemplate rest;
    private HttpHeaders headers;

    public ClientMethod(@Value("${auth-schema}") String authSchema,
                        @Value("${api-key}") String apiKey) {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Auth-Schema", authSchema);
        headers.add("Api-Key", apiKey);
    }

    public String get(String endpoint) throws JSONException, JsonProcessingException {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(endpoint, HttpMethod.GET, requestEntity, String.class);
        return extractPayload(responseEntity.getBody());
    }

    public String post(String endpoint, String json) throws JSONException, JsonProcessingException {
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(endpoint, HttpMethod.POST, requestEntity, String.class);
        return extractPayload(responseEntity.getBody());
    }

    private String extractPayload(String bodyResponse) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(bodyResponse).get("payload");
        return jsonNode.get("list") != null ? jsonNode.get("list").toString() : jsonNode.toString();
    }
}
