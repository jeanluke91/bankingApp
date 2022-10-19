package it.dantonio.bankingapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.dantonio.bankingapp.utils.ResponseCode;
import java.util.HashMap;
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

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceTest {

  @Autowired
  private MockMvc mvc;

  @Value("${test-endpoint-transactions}")
  private String endpoint;

  @Value("${test-accountId}")
  private String accountId;

  @Test
  public void getTransactionsWithResults()
      throws Exception {
    mvc.perform(
            get(endpoint.replace("{accountId}", accountId))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void getTransactionsWithNoResults()
      throws Exception {
    ResultActions result = mvc.perform(
        get(endpoint.replace("{accountId}", accountId))
            .contentType(MediaType.APPLICATION_JSON));

    MvcResult res = result.andReturn();

    HashMap rspContent = new ObjectMapper().readValue(
        res.getResponse().getContentAsString(), HashMap.class);

    Assertions.assertEquals(rspContent.get("rsp_code"),
        ResponseCode.NO_TRANSACTION_FOUND.getCode());
  }

}
