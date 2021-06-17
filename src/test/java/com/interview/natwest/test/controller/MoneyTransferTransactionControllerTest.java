package com.interview.natwest.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.interview.natwest.test.model.MoneyTransferTransaction;
import com.interview.natwest.test.to.StatementTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class MoneyTransferTransactionControllerTest {

	@Test
	public void money_transfer_test() throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("origin", "ddfbf359-5e80-4538-b35e-03960f2b7e54");
		map.add("destination", "640cf087-59f8-4ee5-be9d-939dc3a5d886");
		map.add("amount", "20");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> moneyTransferResponse = restTemplate.postForEntity( "http://localhost:9999/natwest/money-transfer", request , String.class );
		
		assertEquals(HttpStatus.OK, moneyTransferResponse.getStatusCode());
		
		Thread.sleep(10000);

		ResponseEntity<StatementTO> statementResponse = restTemplate.getForEntity( "http://localhost:9999/natwest/account-statement/?origin=ddfbf359-5e80-4538-b35e-03960f2b7e54", StatementTO.class );
	
		assertEquals(1, statementResponse.getBody().getMoneyTransferTransaction().size());
		Assertions.assertEquals(new BigDecimal(20), statementResponse.getBody().getMoneyTransferTransaction().get(0).getAmount());

		Thread.sleep(10000);

		ResponseEntity<MoneyTransferTransaction> transactionApprove = restTemplate.getForEntity( "http://localhost:9999/natwest/approve-transaction/?transactionID=1", MoneyTransferTransaction.class );
		System.out.println(transactionApprove);

		assertEquals("APPROVED", transactionApprove.getBody().getStatus());
//		Assertions.assertEquals(new BigDecimal(20), statementResponse.getBody().getMoneyTransferTransaction().get(0).getAmount());

	}
	
	
}
