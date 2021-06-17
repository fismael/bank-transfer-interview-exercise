package com.interview.natwest.test.controller;

import com.interview.natwest.test.bo.AccountStatementBO;
import com.interview.natwest.test.to.StatementTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountStatementController {

    @Autowired
    AccountStatementBO accountStatementBO;


    @GetMapping("natwest/account-statement")
    @SneakyThrows
    public StatementTO accountStatement(@RequestParam(name="origin", required = true) UUID origin) {
        return accountStatementBO.process(origin);
    }
}
