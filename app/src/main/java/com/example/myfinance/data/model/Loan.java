package com.example.myfinance.data.model;

public class Loan {
    private final String accountNumber;
    private final double initialBalance, currentBalance, interestRate, paymentAmount;

    public Loan(String accountNumber, double initialBalance, double currentBalance, double interestRate, double paymentAmount) {
        this.accountNumber = accountNumber;
        this.initialBalance = initialBalance;
        this.currentBalance = currentBalance;
        this.interestRate = interestRate;
        this.paymentAmount = paymentAmount;
    }

    // Getters
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

    public double getPaymentAmount() {
        return paymentAmount;
    }
}
