package com.talentsprint.cycleshop.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import lombok.Data;

@Entity

@Data

public class OrderItem {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @ManyToOne

    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
