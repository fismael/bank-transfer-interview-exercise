package com.interview.natwest.test.bo;

import com.interview.natwest.test.model.Account;
import com.interview.natwest.test.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class AccountValidationBO {

    @Autowired
    AccountRepository accountRepository;

    public boolean isAccountExists(UUID accountID) {
        Optional<Account> account = accountRepository.findById(accountID);
        return account.isPresent();
    }
}
