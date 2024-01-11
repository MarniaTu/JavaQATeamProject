package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldThrowExceptionIfNegativeInitialBalance() {
        CreditAccount account = new CreditAccount(-10_000, 5_000, 15);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(3_000);
        });

    }

    @Test
    public void shouldThrowExceptionIfNegativeCreditLimit() {
        CreditAccount account = new CreditAccount(10_000, -5_000, 15);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(3_000);
        });
    }

    @Test
    public void shouldThrowExceptionIfNegativeRate() {
        CreditAccount account = new CreditAccount(10_000, 5_000, -10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(3_000);
        });
    }

    @Test
    public void shouldNotPayZero() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.pay(0);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldNotPayNegativeAmount() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.pay(-1000);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldPay10000WithinPositiveBalance() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.pay(10_000);

        Assertions.assertEquals(0, account.getBalance());

    }

    @Test
    public void shouldPay14990WithinCreditLimit() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.pay(14_990);

        Assertions.assertEquals(-4990, account.getBalance());
    }

    @Test
    public void shouldNotPayIfEqualToCreditLimit() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.pay(15_000);

        Assertions.assertEquals(-5000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfExceedsCreditLimit() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.pay(15_500);

        Assertions.assertEquals(-5500, account.getBalance());
    }

    @Test
    public void shouldNotAddZero() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.add(0);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldNotAddNegativeAmount() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.add(-1);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldAddPositiveAmount() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 10);

        account.add(1_500);

        Assertions.assertEquals(11_500, account.getBalance());
    }

    @Test
    public void shouldCalculateYearChangeFromNegativeBalance() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 15);
        account.pay(10_200);

        account.yearChange();

        Assertions.assertEquals(-30, account.yearChange());

    }

    @Test
    public void shouldNotCalculateYearChangeFromZeroBalance() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 15);
        account.pay(10_000);

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldNotCalculateYearChangeFromPositiveBalance() {
        CreditAccount account = new CreditAccount(10_000, 5_000, 16);

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }
}
