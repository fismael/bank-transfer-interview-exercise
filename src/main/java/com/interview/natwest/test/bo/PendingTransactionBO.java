package com.interview.natwest.test.bo;

import com.interview.natwest.test.model.MoneyTransferTransaction;
import com.interview.natwest.test.repository.MoneyTransferTransactionRepository;
import com.interview.natwest.test.util.AccountBalanceOperationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

import static com.interview.natwest.test.util.AccountBalanceOperationConstants.SUBTRACT_AMOUNT;

@Slf4j
@Component
public class PendingTransactionBO {

    private static final String PENDING_STATUS = "PENDING";
    @Autowired
    MoneyTransferTransactionRepository moneyTransferTransactionRepository;

    @Autowired
    AccountValidationBO accountValidationBO;

    @Autowired
    AccountBalanceBO accountBalanceBO;

    public void createPendingTransaction(UUID originAccountID, UUID destinationAccountID, BigDecimal amount) {
        if(isOriginAndDestinationAccountValid(originAccountID, destinationAccountID)) {
            processNewPendingTransaction(originAccountID, destinationAccountID, amount);
        } else {
            throw new IllegalArgumentException("Error! Invalid accounts where provided!");
        }

    }

    private boolean isOriginAndDestinationAccountValid(UUID originAccountID, UUID destinationAccountID) {
        return accountValidationBO.isAccountExists(originAccountID) &&
                accountValidationBO.isAccountExists(destinationAccountID);
    }

    private void processNewPendingTransaction(UUID originAccountID, UUID destinationAccountID, BigDecimal amount) {
        savePendingTransaction(originAccountID, destinationAccountID, amount);

        reserveAmountInOriginAccount(originAccountID, amount);

    }

    private void savePendingTransaction(UUID originAccountID, UUID destinationAccountID, BigDecimal amount) {
        MoneyTransferTransaction moneyTransferTransaction = new MoneyTransferTransaction();
        moneyTransferTransaction.setOrigin(originAccountID);
        moneyTransferTransaction.setDestination(destinationAccountID);
        moneyTransferTransaction.setAmount(amount);
        moneyTransferTransaction.setStatus(PENDING_STATUS);
        moneyTransferTransactionRepository.save(moneyTransferTransaction);
    }

    private void reserveAmountInOriginAccount(UUID originAccountID, BigDecimal amount) {
        accountBalanceBO.updateBankAccountsBalance(originAccountID, amount, SUBTRACT_AMOUNT);
    }
}
