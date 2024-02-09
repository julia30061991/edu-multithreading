package com.concurentbank;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(new AtomicLong(1000));
        System.out.println("Аккаунт создан: " + account1 + ", начальная сумма: " + 1000);
        BankAccount account2 = bank.createAccount(new AtomicLong(500));
        System.out.println("Аккаунт создан: " + account2 + ", начальная сумма: " + 500);

        bank.transfer(account1, account2, new AtomicLong(200));
        bank.transfer(account2, account1, new AtomicLong(100));

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, new AtomicLong(200)));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, new AtomicLong(100)));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}