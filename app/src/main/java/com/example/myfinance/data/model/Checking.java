package com.example.myfinance.data.model;

public class Checking {
    private final String accountNumber;
    private final double currentBalance;

    public Checking(String accountNumber, double currentBalance) {
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
}
