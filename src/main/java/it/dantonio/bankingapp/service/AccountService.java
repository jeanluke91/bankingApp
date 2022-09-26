package it.dantonio.bankingapp.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import it.dantonio.bankingapp.model.Account;
import it.dantonio.bankingapp.model.AccountBalance;
import it.dantonio.bankingapp.model.AccountTransaction;
import it.dantonio.bankingapp.utils.HttpMethod;
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


    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
        }
    }).create();

    public List<Account> getAllAccounts() throws IOException, JSONException {
        logger.log(Level.INFO, "AccountService - getAllAccounts started ");
        String uri = "/api/gbs/banking/v4.0/accounts";
        List<Account> accounts = null;

        JSONObject jsonResponse = httpMethod.doGet(uri);

        if (jsonResponse != null) {
            String jsonAccountList = jsonResponse.getJSONArray("list").toString();
            Type listType = new TypeToken<List<Account>>() {
            }.getType();
            accounts = gson.fromJson(jsonAccountList, listType);

        }
        logger.log(Level.INFO, "AccountService - getAllAccounts finished");
        return accounts;

    }

    public Account getAccountById(Long accountId) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountService - getAccountById started ");

        String uri = "/api/gbs/banking/v4.0/accounts/" + accountId;
        Account account = null;
        JSONObject jsonResponse = httpMethod.doGet(uri);

        if (jsonResponse != null) {
            account = gson.fromJson(jsonResponse.toString(), Account.class);

        }
        logger.log(Level.INFO, "AccountService - getAccountById finished");

        return account;

    }

    public AccountBalance getAccountBalanceById(Long accountId) throws IOException, JSONException {
        logger.log(Level.INFO, "AccountService - getAccountBalanceById started ");

        String uri = "/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";
        AccountBalance accountBalance = null;
        JSONObject jsonResponse = httpMethod.doGet(uri);

        if (jsonResponse != null) {
            accountBalance = gson.fromJson(jsonResponse.toString(), AccountBalance.class);

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
            String jsonAccountTransactionsList = jsonResponse.getJSONArray("list").toString();
            Type listType = new TypeToken<List<AccountTransaction>>() {
            }.getType();
            accountTransactions = gson.fromJson(jsonAccountTransactionsList, listType);

        }
        logger.log(Level.INFO, "AccountService - getTransactions finished");
        return accountTransactions;

    }
}
