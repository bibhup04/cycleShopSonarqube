package com.talentsprint.cycleshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private int stock;
    private int numBorrowed;
    private int price;

    public int getNumAvailable() {
        int available = stock - numBorrowed;
        return available >= 0 ? available : 0;
    }

}
