package com.interview.natwest.test.bo;

import com.interview.natwest.test.repository.AccountRepository;
import com.interview.natwest.test.repository.MoneyTransferTransactionRepository;
import com.interview.natwest.test.to.StatementTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.UUID;

@Component
public class AccountStatementBO {
	@Autowired
    AccountRepository accountRepository;
	
	@Autowired
    MoneyTransferTransactionRepository moneyTransferTransactionRepository;

	@SneakyThrows
	public StatementTO process(UUID accountID) {
		StatementTO statementTO = new StatementTO();
		statementTO.setMoneyTransferTransaction(moneyTransferTransactionRepository.retrieveByOrigin(accountID));
		statementTO.setAccount(accountRepository.findById(accountID).get());
		return statementTO;
	}
}
