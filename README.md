# 💳 Bank Management System - Java Console Application

  This is a simple console-based Bank Management System developed using Java. It allows users to perform various banking       operations such as account creation, deposit, withdrawal, balance inquiry, and deletion. It uses file-based persistence      through a CSV file and PIN encryption using SHA-256.

## 📁 Project Structure

    BankManagementSys/
    ├── Bank.java/
    │   ├── Main.java
    │   ├── BankApplication.java
    │   ├── BankService.java
    │   ├── FileHandler.java
    │   └── Account.java
    ├── data/
    │   └── accounts.csv (Auto-generated on first run)

## 🚀 Features

   - ✅ Create new account
  
   - 💰 Deposit money
     
   - 🏧 Withdraw money
     
   - 📈 Check balance
     
   - 📋 View all accounts
     
   - 🗑️ Delete single or all accounts
     
   - 🔐 PINs are hashed using SHA-256 for security
     
   - 💾 Data is stored in a CSV file (data/accounts.csv)

## 🧪 How to Run
 
   1. Open your terminal or IDE (Eclipse/IntelliJ)
    
   2. Ensure all .java files are in the Bank.java package.
    
   3. Compile the code:

     javac Bank.java/*.java
      
   5. Run the main file:

     java Bank.java.Main

## 📌 Functional Flow

   Upon running, the console displays:

     ----------------------------------------------------
     - Press 1: To create an account
     - press 2: To deposit money
     - press 3: To withdraw money
     - press 4: To check balance
     - press 5: To view all accounts
     - press 6: To delete a single account at a time
     - press 7: To delete all accounts at once
     - press 8: To exit
    ----------------------------------------------------

## 🗃️ CSV File (data/accounts.csv)

  This file stores user account details and is automatically created when the application runs for the first time.

  Format:

    accountNumber,accountHolderName,hashedPin,balance
    12345678901,John Doe,e99a18c428cb38d5f260853678922e03,5000.0

- accountNumber: Unique account ID (auto-generated)

- accountHolderName: User's name

- hashedPin: 6-digit PIN hashed using SHA-256

- balance: Current balance

 🔐 Note: PINs are never stored in plaintext. They are hashed before saving.

## 📦 Technologies Used

- Java 8+

- SHA-256 hashing (MessageDigest)

- File handling (BufferedWriter, BufferedReader)

- CSV format for data storage
