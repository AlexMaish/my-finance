package com.example.myfinance.data.model;

public class CD {
    private final String accountNumber;
    private final double initialBalance, currentBalance, interestRate;

    public CD(String accountNumber, double initialBalance, double currentBalance, double interestRate) {
        this.accountNumber = accountNumber;
        this.initialBalance = initialBalance;
        this.currentBalance = currentBalance;
        this.interestRate = interestRate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
