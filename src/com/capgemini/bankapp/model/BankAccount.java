package com.capgemini.bankapp.model;

import com.capgemini.bankapp.exception.DebitLimitExceedsException;
import com.capgemini.bankapp.exception.LowBalanceException;

public class BankAccount {

	private long accountId;
	private String accountHolderName;
	private String accountType;
	private double accountBalance;

	public BankAccount() {
		super();

	}

	public BankAccount(long accountId, String accountHolderName, String accountType, double accountBalance) {
		super();
		this.accountId = accountId;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double withDraw(double amount) throws LowBalanceException, DebitLimitExceedsException {
		if (accountBalance >= amount)
			accountBalance = accountBalance - amount;
		else
			throw new LowBalanceException("You dont have sufficient balance");
			
		return accountBalance;
	}

	public double deposit(double amount) {
		accountBalance = accountBalance + amount;
		return accountBalance;
	}

}
