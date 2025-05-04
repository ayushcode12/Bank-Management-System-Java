package Bank.java;

public class Account {

	private long accountNumber;
	private String accountHolderName;
	private double balance;
	private String pin;

	public Account(long accNo, String accountHolderName, double balance, String pin) {
		this.accountNumber=accNo;
		this.accountHolderName=accountHolderName;
		this.balance=balance;
		this.pin=pin;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber=accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName=accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance=balance;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin=pin;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName + ", balance="
				+ balance + ", pin=" + pin + "]";
	}

}
