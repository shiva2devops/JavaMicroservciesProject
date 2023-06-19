package com.rama.app;

import org.springframework.stereotype.Component;

@Component
public class UserData {
    String name;
    String age;
    String address;
    String mobile;
    String salary;
    String account;
    public UserData() {
    }
    public UserData(String name, String age, String address, String mobile, String salary, String account) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.mobile = mobile;
        this.salary = salary;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSalary() {
        return salary;
    }

    public String getAccount() {
        return account;
    }


}
