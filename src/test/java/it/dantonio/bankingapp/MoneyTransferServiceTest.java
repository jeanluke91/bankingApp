package it.dantonio.bankingapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.dantonio.bankingapp.utils.ResponseCode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class MoneyTransferServiceTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  ObjectMapper objectMapper;


  @Value("${test-endpoint-money-transfer}")
  private String endpoint;

  @Value("${test-accountId}")
  private String accountId;


  private final static String MONEY_TRANSFER_JSON = "money_transfer_payload.json";

  @Test
  public void createMoneyTransferWithError()
      throws Exception {

    String json = readFromJsonFile(MONEY_TRANSFER_JSON);

    ResultActions result = mvc.perform(
        MockMvcRequestBuilders.post(endpoint.replace("{accountId}",accountId))
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    MvcResult res = result.andReturn();

    HashMap rspContent = objectMapper.readValue(
        res.getResponse().getContentAsString(), HashMap.class);

    Assertions.assertEquals(rspContent.get("rsp_code"),
        ResponseCode.MONEY_TRANSFER_ERROR.getCode());
  }


  private String readFromJsonFile(String fileName) throws IOException, URISyntaxException {

    Path path = Paths.get(getClass().getClassLoader()
        .getResource(fileName).toURI());

    Stream<String> lines = Files.lines(path);
    String payload = lines.collect(Collectors.joining("\n"));
    lines.close();

    return payload;
  }

}
