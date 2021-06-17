package com.interview.natwest.test.controller;

import com.interview.natwest.test.bo.MoneyTransferBO;
import com.interview.natwest.test.bo.RejectedTransactionBO;
import com.interview.natwest.test.bo.SuccessfulTransactionBO;
import com.interview.natwest.test.model.MoneyTransferTransaction;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@RestController
public class TransactionStatusUpdateController {

    @Autowired
    RejectedTransactionBO rejectedTransactionBO;

    @Autowired
    SuccessfulTransactionBO successfulTransactionBO;


    @GetMapping("natwest/approve-transaction")
    @SneakyThrows
    public MoneyTransferTransaction approveTransaction(@RequestParam(name="transactionID", required = true) BigInteger transactionID) {
        return successfulTransactionBO.approveTransaction(transactionID);
    }

    @PostMapping("natwest/reject-transaction")
    @SneakyThrows
    public void rejectTransaction(@RequestParam(name="transactionID", required = true) BigInteger transactionID) {

    }

    @PostMapping("natwest/expire-transaction")
    @SneakyThrows
    public void expireTransaction(@RequestParam(name="transactionID", required = true) BigInteger transactionID) {

    }
}
