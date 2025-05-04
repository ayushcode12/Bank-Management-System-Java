package Bank.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApplication {

	public void main() {

		FileHandler fh=new FileHandler();
		fh.initializeFile();
		BankService bs=new BankService(fh);

		String name;
		String pin;
		double deposit;
		long accNo;
		double withDraw;

		@SuppressWarnings("resource")
		Scanner s=new Scanner(System.in);

		int choice=0;

		do {
			System.out.println("----------------------------------------------------");
			System.out.println(" Press 1: To create an account");
			System.out.println(" press 2: To deposit money");
			System.out.println(" press 3: To withdraw money");
			System.out.println(" press 4: To check balance");
			System.out.println(" press 5: To view all accounts");
			System.out.println(" press 6: To delete a single account at a time");
			System.out.println(" press 7: To delete all accounts as once");
			System.out.println(" press 8: To exit");
			System.out.println("----------------------------------------------------");
			System.out.println("\n");
			System.out.println("----------------------------------------------------");
			System.out.println("press any number between 1 to 8 for desired output");
			System.out.println("----------------------------------------------------");

			choice=s.nextInt();
			s.nextLine();

			switch(choice) {
			case 1:
				try {
					System.out.println("Enter name");
					name = s.nextLine();
					System.out.println("Enter your 6-digit Pin");
					pin=s.next();
					while(pin.length()!=6) {
						System.out.println("Enter your valid 6-digit Pin");
						pin=s.next();
					}
					System.out.println("Enter initial deposit amount");
					deposit=s.nextDouble();

					bs.createAccount(name, pin, deposit);
				}
				catch(InputMismatchException e) {
					System.out.println("Please enter correct input");
				}

			break;

			case 2:
				try {	
					System.out.println("Enter Account Number");
					accNo=s.nextLong();
					System.out.println("Enter your 6-digit PIN");
					pin=s.next();
					System.out.println("Enter amount you want to deposit");
					deposit=s.nextDouble();
					bs.depositMoney(accNo,pin,deposit);
				}
				catch(InputMismatchException e) {
					System.out.println("Please enter correct input");
				}

			break;

			case 3: 
				try {	
					System.out.println("Enter Account Number");
					accNo=s.nextLong();
					System.out.println("Enter your 6-digit PIN");
					pin=s.next();
					System.out.println("Enter amount you want to WithDraw");
					withDraw=s.nextDouble();
					bs.withdrawMoney(accNo,pin,withDraw);
				}
				catch(InputMismatchException e) {
					System.out.println("Please enter correct input");
				}

			break;

			case 4:
				try {					
					System.out.println("Enter Account Number");
					accNo=s.nextLong();
					System.out.println("Enter your 6-digit PIN");
					pin=s.next();
					bs.checkBalance(accNo,pin);
				}
				catch(InputMismatchException e) {
					System.out.println("Please enter correct input");
				}
			break;

			case 5:
				bs.viewAllAccounts();
			break;

			case 6:
				System.out.println("Enter account number of account you want to delete");
				accNo=s.nextLong();
				System.out.println("Enter 6-digit pin");
				pin=s.next();
				bs.deleteAccount(accNo, pin);
			break;

			case 7:
				System.out.println("Are you sure? Type YES to confirm:\nType NO to cancel deletion");
				String confirm=s.next();
				if(confirm.equalsIgnoreCase("YES")) {
					bs.deleteAllAccount();
				}
				else if(confirm.equalsIgnoreCase("NO")) {
					System.out.println("Deletion cancelled");
				}
				else {
					System.out.println("Invalid Input!\nTry again by pressing 7");
				}
			break;

			case 8: 
				bs.fh.saveAccounts(bs.getAccounts());
				System.out.println("Thank you for using the Bank Management System");
			break;	

			default: System.out.println("invalid option! please enter between 1 to 6");
			}

			System.out.println();

		}while(choice!=8);

		s.close();
	}
}
