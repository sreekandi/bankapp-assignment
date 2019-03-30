package com.capgemini.bankapp.model;

import com.capgemini.bankapp.exception.DebitLimitExceedsException;
import com.capgemini.bankapp.exception.LowBalanceException;

public class SavingsBankAccount extends BankAccount {

	private boolean isSalaryAccount;
	private static final double minimumAmount=1000;

	public SavingsBankAccount() {
		super();

	}

	public SavingsBankAccount(long accountId, String accountHolderName, String accountType, boolean isSalaryAccount,
			double accountBalance) {
		super(accountId, accountHolderName, "SAVINGS", accountBalance);
		this.isSalaryAccount = isSalaryAccount;
	}
	

	public boolean isSalaryAccount() {
		return isSalaryAccount;
	}

	public void setSalaryAccount(boolean isSalaryAccount) {
		this.isSalaryAccount = isSalaryAccount;
	}

	public static double getMinimumamount() {
		return minimumAmount;
	}

	@Override
	public double withDraw(double amount) throws LowBalanceException, DebitLimitExceedsException {
		if (isSalaryAccount)
			return super.withDraw(amount);
		else {
			
			if(getAccountBalance()-amount>minimumAmount)
				return super.withDraw(amount);
			else
				throw new LowBalanceException("You dont have sufficient balance");
		}

	}
	
	@Override
	public double deposit(double amount) {
		return super.deposit(amount);
	}

}
