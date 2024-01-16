package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {
    Bank bank = new Bank();

    @Test
    public void shouldTransferIfAmountBelowBalance() {
        Account from = new SavingAccount(5_000, 0, 10_000, 12);
        Account to = new CreditAccount(1_000, 5_000, 17);

        bank.transfer(from, to, 1_000);

        Assertions.assertEquals(5_000 - 1_000, from.getBalance());
        Assertions.assertEquals(1_000 + 1_000, to.getBalance());

    }

    @Test
    public void shouldTransferFromCreditToSavingWithinCreditLimit() {
        Account from = new CreditAccount(1_000, 5_000, 17);
        Account to = new SavingAccount(5_000, 0, 10_000, 12);

        bank.transfer(from, to, 2_000);

        Assertions.assertEquals(1_000 - 2_000, from.getBalance());
        Assertions.assertEquals(5_000 + 2_000, to.getBalance());

    }

    @Test
    public void shouldNotTransferZeroFromSaving() {
        Account from = new SavingAccount(3_000, 0, 10_000, 12);
        Account to = new CreditAccount(1_000, 5_000, 17);

        bank.transfer(from, to, 0);

        Assertions.assertEquals(3_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }

    @Test
    public void shouldNotTransferNegativeAmountFromCredit() {
        Account from = new CreditAccount(1_000, 5_000, 17);
        Account to = new SavingAccount(3_000, 0, 10_000, 12);

        bank.transfer(from, to, -2_000);

        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(3_000, to.getBalance());
    }

    @Test
    public void shouldNotTransferFromSavingToCreditBelowMinBalance() {
        Account from = new SavingAccount(2_000, 0, 10_000, 12);
        Account to = new CreditAccount(1_000, 5_000, 17);

        bank.transfer(from, to, 2_500);

        Assertions.assertEquals(2_000, from.getBalance());
        Assertions.assertEquals(1_000, to.getBalance());
    }

    @Test
    public void shouldNotTransferFromCreditToSavingAboveMaxBalance() {
        Account from = new CreditAccount(1_000, 5_000, 17);
        Account to = new SavingAccount(7_000, 0, 10_000, 12);

        bank.transfer(from, to, 4_000);

        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(7_000, to.getBalance());

    }

    @Test
    public void shouldNotTransferFromCreditToSavingBelowCreditLimit() {
        Account from = new CreditAccount(2_000, 5_000, 17);
        Account to = new SavingAccount(2_000, 0, 10_000, 12);

        bank.transfer(from, to, 7_500);

        Assertions.assertEquals(2_000, from.getBalance());
        Assertions.assertEquals(2_000, to.getBalance());

    }
}
