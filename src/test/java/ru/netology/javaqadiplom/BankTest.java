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

        Assertions.assertEquals(true, from.pay(1_000));
        Assertions.assertEquals(true, to.add(1_000));

    }

    @Test
    public void shouldTransferFromCreditToSavingWithinCreditLimit() {
        Account to = new SavingAccount(5_000, 0, 10_000, 12);
        Account from = new CreditAccount(1_000, 5_000, 17);


        bank.transfer(from, to, 2_000);

        Assertions.assertEquals(true, from.pay(2_000));
        Assertions.assertEquals(true, to.add(2_000));

    }

    @Test
    public void shouldNotTransferZeroFromSaving() {
        Account from = new SavingAccount(3_000, 0, 10_000, 12);
        Account to = new CreditAccount(1_000, 5_000, 17);

        bank.transfer(from, to, 0);

        Assertions.assertEquals(false, from.pay(0));
        Assertions.assertEquals(false, to.add(0));
    }

    @Test
    public void shouldNotTransferNegativeAmountFromCredit() {
        Account to = new SavingAccount(3_000, 0, 10_000, 12);
        Account from = new CreditAccount(1_000, 5_000, 17);

        bank.transfer(from, to, -2_000);

        Assertions.assertEquals(false, from.pay(-2_000));
        Assertions.assertEquals(false, to.add(-2_000));
    }

    @Test
    public void shouldNotTransferFromSavingToCreditBelowMinBalance() {
        Account from = new SavingAccount(2_000, 0, 10_000, 12);
        Account to = new CreditAccount(1_000, 5_000, 17);

        bank.transfer(from, to, 2_500);

        Assertions.assertEquals(false, from.pay(2_500));
        Assertions.assertEquals(false, to.add(2_500));
    }

    @Test
    public void shouldNotTransferFromCreditToSavingAboveMaxBalance() {
        Account to = new SavingAccount(7_000, 0, 10_000, 12);
        Account from = new CreditAccount(1_000, 5_000, 17);

        bank.transfer(from, to, 4_000);

        Assertions.assertEquals(false, from.pay(4_000));
        Assertions.assertEquals(false, to.add(4_000));

    }

    @Test
    public void shouldNotTransferFromCreditToSavingBelowCreditLimit() {
        Account to = new SavingAccount(2_000, 0, 10_000, 12);
        Account from = new CreditAccount(2_000, 5_000, 17);

        bank.transfer(from, to, 7_500);

        Assertions.assertEquals(false, from.pay(7_500));
        Assertions.assertEquals(false, to.add(7_500));

    }
}
