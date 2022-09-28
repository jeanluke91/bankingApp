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

    @Value("${endpoint-account-balance}")
    private String endpoint;

    Logger logger = Logger.getLogger(AccountService.class.getName());

    public AccountBalance getAccountBalanceById(Long accountId) throws IOException {
        logger.log(Level.INFO, "AccountService - getAccountBalanceById started ");
        String url = endpoint.replace("{accountId}", String.valueOf(accountId));
        AccountBalance accountBalance = null;
        String r = null;
        try {
            r = clientMethod.get(url);
            accountBalance = new ObjectMapper().registerModule(new JavaTimeModule())
                    .readValue(r, AccountBalance.class);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "AccountService - getAccountBalanceById finished");

        return accountBalance;

    }

}
