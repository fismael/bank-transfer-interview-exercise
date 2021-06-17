# bank-transfer-interview-exercise


#Description:
This project represents the money transfer between two bank accounts.
The main idea is to reserve the money before actually making the transfer.

The algorithm is:
  1. Check if both accounts exists
  2. Check if the origin account has the appropriate balance
  3. Reserve the ammount in a transaction table with the status PENDING
  4. Once the transaction is APPROVED, the money is moved to the destination account and the transaction is marked as APPROVED 
    (which helps preventing the transaction to be processed more than once).


Instructions to run:
1. Clone the project
2. Run Maven update.
3. Start the Spring Boot Server -> src/main/java/com/interview/natwest/test/TestApplication.java
4. Run the end-to-end test -> src/test/java/com/interview/natwest/test/controller/MoneyTransferTransactionControllerTest.java
  4.1. This test shows that the money is reserved
  4.2. The transaction is approved
  4.3. The money is added into the destination account.
Note: this test should be run only once. If we run again it will fail because we will have 2 transactions instead of 1.
In order to re-run the test we need to Restart the App server (which will clear the DB) or clear the DB.
