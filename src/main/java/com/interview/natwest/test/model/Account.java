package com.interview.natwest.test.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
public class Account {

	@Column(name = "number")
	@Id
	private UUID number;
	
	@Column(name = "balance")
	private BigDecimal balance;
}
