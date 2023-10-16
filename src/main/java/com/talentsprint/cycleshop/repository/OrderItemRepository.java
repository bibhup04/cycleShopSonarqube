package com.talentsprint.cycleshop.repository;

import com.talentsprint.cycleshop.entity.OrderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

// import com.learning.restapi.entity.CartItem;

import org.springframework.data.jpa.repository.Query;

@Repository

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // @Query("SELECT ci FROM CartItem ci " +

    // "JOIN ci.transaction tr " +

    // "JOIN tr.user u " +

    // "JOIN ci.cart cart " +

    // "WHERE u.id = :userId " +

    // "AND tr.isReturned = false " +

    // "AND cart.booked = true " +

    // "AND cart.returned = false")

    // List<CartItem> findCartItemsByUserIdAndConditions(

    // @Param("userId") Long userId

    // );

}