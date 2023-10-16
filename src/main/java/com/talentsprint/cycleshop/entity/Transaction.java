package com.talentsprint.cycleshop.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import lombok.Data;

import java.time.LocalDateTime;

@Entity

@Data

public class Transaction {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @ManyToOne

    @JoinColumn(name = "user_id")

    private User user;

    private double totalAmount;

    private double remainingAmount;

    private boolean isReturned;

    private LocalDateTime rentalStartTime;

    private LocalDateTime rentalEndTime;

}
