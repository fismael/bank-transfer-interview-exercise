package com.interview.natwest.test.to;

import java.util.List;

import com.interview.natwest.test.model.MoneyTransferTransaction;
import com.interview.natwest.test.model.Account;
import lombok.Data;

@Data
public class StatementTO {
	private Account account;
	private List<MoneyTransferTransaction> moneyTransferTransaction;
}
