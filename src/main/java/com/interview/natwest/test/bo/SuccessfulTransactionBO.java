package com.interview.natwest.test.bo;

import com.interview.natwest.test.model.Account;
import com.interview.natwest.test.model.MoneyTransferTransaction;
import com.interview.natwest.test.repository.AccountRepository;
import com.interview.natwest.test.repository.MoneyTransferTransactionRepository;
import com.interview.natwest.test.util.AccountBalanceOperationConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

import static com.interview.natwest.test.util.AccountBalanceOperationConstants.ADD_AMOUNT;

@Slf4j
@Component
public class SuccessfulTransactionBO {
    private static final String PENDING_STATUS = "PENDING";
    @Autowired
    MoneyTransferTransactionRepository moneyTransferTransactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountBalanceBO accountBalanceBO;

    @SneakyThrows
    public MoneyTransferTransaction approveTransaction(BigInteger transactionID) {
        MoneyTransferTransaction moneyTransferTransaction =
                moneyTransferTransactionRepository.findById(transactionID).get();



        addMoneyToDestinationAccount(moneyTransferTransaction);

        markTransactionAsApproved(moneyTransferTransaction);

        return moneyTransferTransaction;
    }

    private void addMoneyToDestinationAccount(MoneyTransferTransaction moneyTransferTransaction) {
        if(PENDING_STATUS.equals(moneyTransferTransaction.getStatus())) {
            Account destinationAccount =
                    accountRepository.findById(moneyTransferTransaction.getDestination()).get();

            accountBalanceBO.updateBankAccountsBalance(destinationAccount.getNumber(), moneyTransferTransaction.getAmount(), ADD_AMOUNT);
        } else {
            throw new IllegalArgumentException("Transaction already processed!");
        }
    }

    private void markTransactionAsApproved(MoneyTransferTransaction moneyTransferTransaction) {
        moneyTransferTransaction.setStatus("APPROVED");
        moneyTransferTransactionRepository.save(moneyTransferTransaction);
    }

}
