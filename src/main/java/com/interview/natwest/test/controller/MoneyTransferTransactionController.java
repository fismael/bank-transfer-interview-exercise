package com.interview.natwest.test.controller;

import com.interview.natwest.test.bo.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.natwest.test.to.StatementTO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;


@RestController
public class MoneyTransferTransactionController {

	@Autowired
	MoneyTransferBO moneyTransferTransactionBO;

	@PostMapping("natwest/money-transfer")
	@SneakyThrows
	public void transferMoney(@RequestParam(name="origin", required = true) UUID originAccountID, @RequestParam(name="destination", required = true) UUID destinationAccountID, @RequestParam(name="amount", required = true) BigDecimal amount) {
		moneyTransferTransactionBO.process(originAccountID, destinationAccountID, amount);
	}
}
