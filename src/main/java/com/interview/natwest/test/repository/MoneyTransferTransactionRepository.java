package com.interview.natwest.test.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.interview.natwest.test.model.MoneyTransferTransaction;

public interface MoneyTransferTransactionRepository extends JpaRepository<MoneyTransferTransaction, BigInteger> {
	@Query("SELECT t FROM MoneyTransferTransaction t WHERE t.origin = :origin")
	List<MoneyTransferTransaction> retrieveByOrigin(@Param("origin") UUID origin);
	
	
}
