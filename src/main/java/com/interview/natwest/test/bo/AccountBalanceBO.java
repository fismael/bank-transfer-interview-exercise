package com.interview.natwest.test.bo;

import com.interview.natwest.test.model.Account;
import com.interview.natwest.test.repository.AccountRepository;
import com.interview.natwest.test.util.AccountBalanceOperationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AccountBalanceBO {

    @Autowired
    AccountRepository accountRepository;

    public void updateBankAccountsBalance(UUID accountID, BigDecimal amount, AccountBalanceOperationConstants operation) {

        Account account = accountRepository.findById(accountID).get();

        if(account.getBalance().compareTo(amount)>0) {
            switch(operation) {
                case SUBTRACT_AMOUNT:
                    account.setBalance(account.getBalance().subtract(amount));
                    accountRepository.save(account);
                    break;
                case ADD_AMOUNT:
                    account.setBalance(account.getBalance().add(amount));
                    accountRepository.save(account);
                    break;
                default:
                    log.error("Invalid operation {} provided for updating account balance", operation);
            }
        } else {
            throw new IllegalArgumentException("Account does not have enough balance!");
        }

    }
}
