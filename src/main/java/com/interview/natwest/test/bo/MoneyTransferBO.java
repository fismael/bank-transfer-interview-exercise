package com.interview.natwest.test.bo;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.interview.natwest.test.model.Account;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MoneyTransferBO {
	@Autowired
	PendingTransactionBO pendingTransactionBO;

	@SneakyThrows
	public synchronized void process(UUID originAccountID, UUID destinationAccountID, BigDecimal amount) {
		pendingTransactionBO.createPendingTransaction(originAccountID, destinationAccountID, amount);
	}

}
