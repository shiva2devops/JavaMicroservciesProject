package com.account.app.controller;

import com.account.app.model.Account;
import com.account.app.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AccountController {
    private final RepositoryService repositoryService;

    @Autowired
    public AccountController(RepositoryService repositoryService){
        this.repositoryService=repositoryService;
    }

    @GetMapping("/get/account")
    public Account getAccountDetail(@RequestParam("account_id") String account_id){
        return repositoryService.getDetail(account_id);
    }

    @PostMapping("/save/account")
    public Map saveAccount(@RequestBody Account account){
        return repositoryService.saveAccounts(account);
    }

    @PutMapping("/update/account")
    public Account updateAccount(@RequestHeader("account_id") String account_id,@RequestHeader("ammount") int ammount){
        return repositoryService.updateAccount(account_id,ammount);
    }

    @DeleteMapping("/delete/account/{accountid}")
    public Map deleteAccount(@PathVariable String accountid){
        return repositoryService.deleteAccount(accountid);
    }

}
