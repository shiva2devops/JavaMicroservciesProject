package com.account.app.model;

import org.springframework.stereotype.Component;

@Component
public class Account {
    String id;
    int balance;
    String name;
   public Account(){

   }
    public Account(String id, int balance, String name) {
        this.id = id;
        this.balance = balance;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }


}
