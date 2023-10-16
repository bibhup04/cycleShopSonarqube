package com.talentsprint.cycleshop.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.talentsprint.cycleshop.entity.Transaction;
import com.talentsprint.cycleshop.repository.TransactionRepository;

import java.time.LocalDateTime;

import java.util.List;

import java.util.Optional;

@Service

public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired

    private CartService cartService;

    @Autowired

    private UserService userService;

    @Autowired

    public TransactionService(TransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;

    }

    public Transaction createTransaction(Transaction transaction) {

        return transactionRepository.save(transaction);

    }

    public Optional<Transaction> getTransactionById(long id) {

        return transactionRepository.findById(id);

    }

    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();

    }

    public Transaction updateTransaction(Transaction transaction) {

        return transactionRepository.save(transaction);

    }

    public void deleteTransaction(long id) {

        transactionRepository.deleteById(id);

    }

    public void rentCycles(String userName, double totalAmount) {

        Transaction transaction = new Transaction();
        transaction.setUser(userService.getByName(userName).get());
        transaction.setTotalAmount(totalAmount);
        transaction.setRemainingAmount(0);
        transaction.setReturned(false);

        LocalDateTime currentDateTime = LocalDateTime.now();
        transaction.setRentalStartTime(currentDateTime);

        transactionRepository.save(transaction);

        cartService.markCartItemsAsBooked(userService.getByName(userName).get().getId(), transaction);
    }

}
