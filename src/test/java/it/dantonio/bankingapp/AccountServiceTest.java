package it.dantonio.bankingapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountServiceTest {

  @Autowired
  private MockMvc mvc;


  @Value("${test-endpoint-account-balance}")
  private String endpoint;

  @Value("${test-accountId}")
  private String accountId;
  @Test
  public void getAccountBalanceWithResults()
      throws Exception {
    mvc.perform(get(endpoint.replace("{accountId}", accountId))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

    /*
    @Test
    public void getAccountBalanceWithInvalidAccount()
            throws Exception {
        mvc.perform(get("https://localhost:8080/accounts/14930637/balance")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }*/

}
