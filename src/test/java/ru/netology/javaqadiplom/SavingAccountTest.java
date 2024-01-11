package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldThrowExceptionDueToInitialLessThenMinimal() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    1_000,
                    2_000,
                    3_000,
                    5
            );
        });
    }

    @Test
    public void shouldThrowExceptionDueToInitialMoreThenMaximum() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    3_000,
                    1_000,
                    2_000,
                    5
            );
        });
    }

    @Test
    public void shouldThrowExceptionDueToMinMoreThenMax() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    1_000,
                    3_000,
                    2_000,
                    5
            );
        });
    }

    @Test
    public void shouldThrowExceptionDueToNegativeValues() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    -2_000,
                    -3_000,
                    -1_000,
                    5
            );
        });
    }

    @Test
    public void shouldThrowExceptionDueToZeroRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    -2_000,
                    -3_000,
                    -1_000,
                    0
            );
        });
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddUpToMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldNotAddNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddZeroAmount() {
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayNotLessMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(2_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayUpToMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.pay(1_000));
    }
}

