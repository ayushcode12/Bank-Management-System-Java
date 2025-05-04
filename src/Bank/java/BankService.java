package Bank.java;

import java.util.HashMap;
import java.util.Map;

public class BankService {

	private HashMap<Long, Account> accounts = new HashMap<>();

	FileHandler fh=new FileHandler();

	public BankService(FileHandler fh) {
		this.fh=fh;
		this.accounts = fh.loadAccounts();
	}

	public HashMap<Long, Account> getAccounts() {
		return accounts;
	}

	public void createAccount(String name, String pin, double deposit) {
		long AccNo;

		do {
	        AccNo = 10000000000L + (int)(Math.random() * 90000000000L); // 9-digit number
	    } while (accounts.containsKey((long)AccNo));

		String hashedPin=fh.hashPin(pin);
		Account acc=new Account(AccNo, name, deposit, hashedPin);
		accounts.put((long) AccNo, acc);

		System.out.println("Account created successfully! Your account number is: "+AccNo);

		fh.saveAccounts(accounts);

	}


	public void depositMoney(long accNo, String pin, double deposit){
		try {			
			Account temp=accounts.get(accNo);
			String hashedPin=fh.hashPin(pin);

			if(temp==null) {
				System.out.println("Account not foud");
				return;
			}
			else {
				String tempPin = temp.getPin();
				if(tempPin.equals(hashedPin)) {
					temp.setBalance(temp.getBalance() + deposit);

					System.out.println(deposit+" is deposited in your account, Now your current balance is "+temp.getBalance());
				}
				else {
					System.out.println("Incorrect PIN");
					return;
				}
			}
		}
		catch(NullPointerException e) {
			System.out.println("Account not found");
		}

		fh.saveAccounts(accounts);

	}


	public void withdrawMoney(long accNo,String pin,double withDraw){

		try {
			Account temp=accounts.get((long)accNo);
			String hashedPin=fh.hashPin(pin);

			if(temp==null) {
				System.out.println("Account does not exist");
				return;
			}
			else {
				if(hashedPin.equals(temp.getPin())) {
					if(withDraw <= temp.getBalance()) {
						temp.setBalance(temp.getBalance() - withDraw);	
						System.out.println("Your Withdrawn is "+withDraw+"\nNow your current balance is "+temp.getBalance());
					}
					else {
						System.out.println("You dont have enough balance to withdraw "+withDraw);
						return;
					}
				}
				else {
					System.out.println("PIN is incorrect");
					return;
				}
			}
		}
		catch(NullPointerException e) {
			System.out.println("Account not found");
		}

		fh.saveAccounts(accounts);

	}


	public void checkBalance(long accNo, String pin){

		String hashedPin=fh.hashPin(pin);

		try {	
			Account temp=accounts.get((long)accNo);
			if(temp==null) {
				System.out.println("Account does not exist");
				return;
			}
			else {
				if(hashedPin.equals(temp.getPin())) {
					System.out.println("Your current account balance is "+temp.getBalance());
				}
				else {
					System.out.println("PIN is incorrect");
					return;
				}
			}
		}
		catch(NullPointerException e) {
			System.out.println("Account not found");
		}
	}


	public void viewAllAccounts(){
		for(Map.Entry<Long, Account> entry : accounts.entrySet()) {
			Long accNo=entry.getKey();
			Account acc=entry.getValue();
			System.out.println("--------------------------------");
		    System.out.println("Account Number: " + accNo);
		    System.out.println("Name          : " + acc.getAccountHolderName());
		    System.out.println("Balance       : ₹" + acc.getBalance());
		    System.out.println("--------------------------------");
		}

	}

	public void deleteAccount(long accNo, String pin) {
		String hashedPin = fh.hashPin(pin);
		Account temp = accounts.get(accNo);

		if(temp==null) {
			System.out.println("Account does not exist");
			return;
		}
		else {
			if(temp.getPin().equals(hashedPin)) {
				accounts.remove(accNo);
			}
			else {
				System.out.println("Pin is incorrect");
				return;
			}
		}
		fh.saveAccounts(accounts);
		System.out.println("Account "+accNo+" deleted successfully");
	}


	public void deleteAllAccount() {
		accounts.clear();
		fh.saveAccounts(accounts);
		System.out.println("All accounts have been deleted successfully");

	}
}
