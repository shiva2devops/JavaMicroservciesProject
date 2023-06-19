package com.account.app.service;

import com.account.app.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RepositoryService {
    private static final HashMap<String, Account> accounts = new HashMap();

    public Account getDetail(String account_id){
        return accounts.get(account_id);
    }

    public Map saveAccounts(Account account){
        accounts.put(account.getId(),account);
        return accounts;
    }

    public Account updateAccount(String account_id,int amount){
        Account account= accounts.get(account_id);
        account.setBalance(amount);
        return account;
    }

    public Map deleteAccount(String account_id){
        accounts.remove(account_id);
        return accounts;
    }

}
