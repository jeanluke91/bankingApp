package it.dantonio.bankingapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getTransactionsWithoutParams()
      throws Exception {
    mvc.perform(get("https://localhost:8080/transactions/14537780")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getTransactionsWithResults()
      throws Exception {
    mvc.perform(
            get("https://localhost:8080/transactions/14537780?fromAccountingDate=2015-01-01&toAccountingDate=2022-09-20")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void getAccountBalanceWithNoResults()
      throws Exception {
    mvc.perform(
            get("https://localhost:8080/transactions/14537780?fromAccountingDate=2010-01-01&toAccountingDate=2010-09-20")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("[]"));
  }

}
