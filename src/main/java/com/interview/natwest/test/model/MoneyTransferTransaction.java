package com.interview.natwest.test.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "money_transfer_transaction")
@Data
public class MoneyTransferTransaction {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name = "origin")
	private UUID origin;
	
	@Column(name = "destination")
	private UUID destination;
	
	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "status")
	private String status;
}
