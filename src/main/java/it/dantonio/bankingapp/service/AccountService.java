package it.dantonio.bankingapp.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import it.dantonio.bankingapp.model.AccountBalance;
import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.model.MoneyTransferBody;
import it.dantonio.bankingapp.utils.HttpMethod;
import it.dantonio.bankingapp.utils.LocalDateDeserializer;
import it.dantonio.bankingapp.utils.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AccountService {

    @Autowired
    private HttpMethod httpMethod;

    Logger logger = Logger.getLogger(AccountService.class.getName());


    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .setPrettyPrinting().create();

    public AccountBalance getAccountBalanceById(Long accountId) throws IOException {
        logger.log(Level.INFO, "AccountService - getAccountBalanceById started ");

        String uri = "/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";
        AccountBalance accountBalance = null;
        JSONObject jsonResponse = httpMethod.doGet(uri);

        if (jsonResponse != null) {
            accountBalance = gson.fromJson(String.valueOf(jsonResponse), AccountBalance.class);

        }
        logger.log(Level.INFO, "AccountService - getAccountBalanceById finished");

        return accountBalance;

    }

    public List<AccountTransaction> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountService - getTransactions started ");
        String uri = "/api/gbs/banking/v4.0/accounts/" + accountId + "/transactions?fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate;
        List<AccountTransaction> accountTransactions = null;
        JSONObject jsonResponse = httpMethod.doGet(uri);

        if (jsonResponse != null) {
            String jsonAccountTransactionsList = String.valueOf(jsonResponse.getJSONArray("list"));
            Type listType = new TypeToken<List<AccountTransaction>>() {
            }.getType();
            accountTransactions = gson.fromJson(jsonAccountTransactionsList, listType);

        }
        logger.log(Level.INFO, "AccountService - getTransactions finished");
        return accountTransactions;

    }

    public Object createMoneyTransfer(Long accountId, MoneyTransferBody moneyTransferBody) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountService - createMoneyTransfer started ");
        String uri = "/api/gbs/banking/v4.0/accounts/" + accountId + "/payments/money-transfers";

        String body = gson.toJson(moneyTransferBody);
        JSONObject jsonResponse = httpMethod.doPost(uri, body);
        Object obj = null;
        if (jsonResponse != null) {
            obj = gson.fromJson(String.valueOf(jsonResponse), Object.class);

        }
        logger.log(Level.INFO, "AccountService - createMoneyTransfer finished");
        return obj;

    }
}
