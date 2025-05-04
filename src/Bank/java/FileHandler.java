package Bank.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class FileHandler {

	File file;

	public String hashPin(String pin) {
		StringBuilder sb=null;
		try {
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			byte[] hash=md.digest(pin.getBytes());
			sb=new StringBuilder();
			for(byte b : hash) {
				sb.append(String.format("%02x", b));
			}
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void initializeFile() {

		File folder = new File("data");
		if (!folder.exists()) {
            boolean folderCreated = folder.mkdir(); 
            if (folderCreated) {
                System.out.println("Directory created: " + folder.getAbsolutePath());
            } else {
                System.out.println("Failed to create the directory.");
            }
        }

		file = new File("data/accounts.csv");

		if(file.exists()==false) {
			try {
				boolean fileCreated=file.createNewFile();
				BufferedWriter me;

				if(fileCreated) {
					System.out.println("file created: "+ file.getAbsolutePath());

					me=new BufferedWriter(new FileWriter(file));
					me.write("accountNumber,holderName,hashedPin,balance");
					me.newLine();
				}
				else {
                    System.out.println("Failed to create the file.");
                }
			} 
			catch (IOException e) {
				System.out.println(e);
			}
		}
		else {
			System.out.println("File already exists: " + file.getAbsolutePath());
		}

	}

	public void saveAccounts(HashMap<Long, Account> accounts) {

		BufferedWriter me = null;

		try {
			if (file == null || !file.exists()) {
	            System.out.println("File is not initialized or doesn't exist");
	            return;
	        }

			me=new BufferedWriter(new FileWriter(file));
			me.write("accountNumber,accountHolderName,hashedPin,balance");
			me.newLine();
			for(Account acc : accounts.values()) {
				me.write(acc.getAccountNumber()+","+acc.getAccountHolderName()+","+acc.getPin()+","+acc.getBalance());
				me.newLine();
			}
		} 
		catch (IOException e) {
			System.out.println(e);
		}
		finally {
			if(me!=null) {
				try {
					me.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public HashMap<Long, Account> loadAccounts() {

		HashMap<Long, Account> accounts=new HashMap<>();

		BufferedReader me = null;

		try {
			me=new BufferedReader(new FileReader(file));
			me.readLine();
			String line;
			while((line=me.readLine())!= null) {

				String[] parts=line.split(",",4);

				if(parts.length==4) {
					Long accountNumber=Long.parseLong(parts[0]);
					String accountHolderName=parts[1];
					String pin=parts[2];
					double balance=Double.parseDouble(parts[3]);

					Account acc=new Account(accountNumber, accountHolderName, balance, pin);

					accounts.put(accountNumber, acc);
				}
				else {
	                System.out.println("Skipping invalid line: " + line);
				}
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
		catch (IOException e) {
			System.out.println(e);
		}
		finally {
			if(me!=null) {	
				try {
					me.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return accounts;

	}

}