package com.interview.natwest.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.natwest.test.model.Account;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
