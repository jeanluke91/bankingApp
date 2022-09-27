package it.dantonio.bankingapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.dantonio.bankingapp.model.AccountBalance;
import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.model.MoneyTransferBody;
import it.dantonio.bankingapp.utils.ClientMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AccountService {
    @Autowired
    private ClientMethod clientMethod;

    @Value("${endpoint}")
    private String endpoint;

    Logger logger = Logger.getLogger(AccountService.class.getName());

    public AccountBalance getAccountBalanceById(Long accountId) throws IOException {
        logger.log(Level.INFO, "AccountService - getAccountBalanceById started ");
        String url = endpoint.replace("{accountId}", String.valueOf(accountId)).concat("/balance");
        AccountBalance accountBalance = null;
        JSONObject r = null;
        try {
            r = clientMethod.get(url);
            accountBalance = new ObjectMapper().registerModule(new JavaTimeModule())
                    .readValue(r.getString("payload"), AccountBalance.class);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "AccountService - getAccountBalanceById finished");

        return accountBalance;

    }

    public List<AccountTransaction> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountService - getTransactions started ");
        String url = endpoint.replace("{accountId}", String.valueOf(accountId))
                .concat("/transactions?fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate);

        List<AccountTransaction> accountTransactions = null;
        JSONObject r = null;
        try {
            r = clientMethod.get(url);
            List<AccountTransaction> transactions = new ObjectMapper().registerModule(new JavaTimeModule())
                    .readValue(r.getString("payload"), new TypeReference<List<AccountTransaction>>() {
                    });

            AccountBalance a = new ObjectMapper().registerModule(new JavaTimeModule())
                    .readValue(r.getString("payload"), AccountBalance.class);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        logger.log(Level.INFO, "AccountService - getTransactions finished");
        return accountTransactions;

    }

    public Object createMoneyTransfer(Long accountId, MoneyTransferBody moneyTransferBody) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountService - createMoneyTransfer started ");
        String url = endpoint.replace("{accountId}", String.valueOf(accountId))
                .concat("/payments/money-transfers");

        String json = new ObjectMapper().registerModule(new JavaTimeModule())
                .writeValueAsString(moneyTransferBody);

        JSONObject r = clientMethod.post(url, json);

        return null;
    }
}
