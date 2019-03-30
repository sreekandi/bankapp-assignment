package com.capgemini.bankapp.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bankapp.exception.DebitLimitExceedsException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.CurrentBankAccount;
import com.capgemini.bankapp.model.SavingsBankAccount;

public class BankAccountTest {

	private BankAccount account;

	@Before
	public void setUp() {
		account = new BankAccount(001, "joes", "SAVINGS", 15000);

	}

	@Test
	public void testBankAccountObjectCreatedWithDefaultConstructor() {
		BankAccount account = new BankAccount();
		assertNotNull(account);

	}

	@Test
	public void testBankAccountObjectCreatedWithParameterizedConstructor() {
		BankAccount account = new BankAccount(001, "joes", "SAVINGS", 15000);
		assertNotNull(account);
		assertEquals(001, account.getAccountId());
		assertEquals("joes", account.getAccountHolderName());
		assertEquals("SAVINGS", account.getAccountType());
		assertEquals(15000.0, account.getAccountBalance(), 0.01);

	}

	@Test
	public void testBankAccountCurrentAccountBalance() {
		assertEquals(15000, account.getAccountBalance(), 0.01);
	}

	@Test
	public void testWithDrawWithSufficientFund() throws LowBalanceException, DebitLimitExceedsException {
		BankAccount account1 = new SavingsBankAccount(001, "joes", "SAVINGS", true, 15000);
		account1.withDraw(8000);
		assertEquals(7000, account1.getAccountBalance(), 0.01);
	}

	@Test(expected = LowBalanceException.class)
	public void testWithDrawInSufficientFund() throws LowBalanceException, DebitLimitExceedsException {
		BankAccount account1 = new SavingsBankAccount(001, "joes", "SAVINGS", false, 15000);
		account1.withDraw(14500);

	}

	@Test
	public void testDeposit() {
		account.deposit(500);
		assertEquals(15500.0, account.getAccountBalance(), 0.01);
	}

	@Test(expected = DebitLimitExceedsException.class)
	public void testDebitLimitExceedsException() throws LowBalanceException, DebitLimitExceedsException {
		BankAccount account1 = new CurrentBankAccount(001, "joes", 15000, 10000);
		account1.withDraw(26000);
	}
	
	

}
