package com.talentsprint.cycleshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.talentsprint.cycleshop.entity.Transaction;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
