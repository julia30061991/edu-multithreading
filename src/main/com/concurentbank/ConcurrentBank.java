package com.concurentbank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBank {
    private static final List <BankAccount> accountList = new ArrayList<>();

    public ConcurrentBank() {}

    public List<BankAccount> getAccountList() {
        return accountList;
    }

    public BankAccount createAccount(AtomicLong money) {
        BankAccount account = new BankAccount();
        account.setBalance(money);
        accountList.add(account);
        return account;
    }

    public void transfer(BankAccount account1, BankAccount account2, AtomicLong money) {
            if (account1.getBalance().longValue() >= money.longValue()) {
                account1.withdraw(account1, money.longValue());
                account2.deposit(account2, money.longValue());
            } else {
                System.out.println("На счету недостаточно средств для перевода");
            }
    }

    public AtomicLong getTotalBalance() {
        AtomicLong result = new AtomicLong(0);
        for (BankAccount account: accountList) {
            AtomicLong money = account.getBalance();
            result.addAndGet(money.longValue());
        }
        return result;
    }
}