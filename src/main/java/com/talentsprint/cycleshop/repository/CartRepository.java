package com.talentsprint.cycleshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentsprint.cycleshop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser_Id(long userId);

    List<Cart> findByUser_IdAndBookedFalse(long userId);

    Cart findById(long id);
}