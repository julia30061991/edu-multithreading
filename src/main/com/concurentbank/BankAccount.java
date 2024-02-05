package com.concurentbank;

import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {
    private AtomicLong balance;

    public BankAccount() {}

    public void setBalance(AtomicLong balance) {
        this.balance = balance;
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public void deposit(BankAccount account, long money) {
        account.getBalance().addAndGet(money);
    }

    public void withdraw(BankAccount account, long money) {
        account.getBalance().addAndGet(- money);
    }
}