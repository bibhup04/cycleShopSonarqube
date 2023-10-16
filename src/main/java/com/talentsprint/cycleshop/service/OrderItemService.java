package com.talentsprint.cycleshop.service;

import com.talentsprint.cycleshop.entity.Cart;

import com.talentsprint.cycleshop.entity.OrderItem;

import com.talentsprint.cycleshop.entity.Transaction;

import com.talentsprint.cycleshop.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service

public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired

    public OrderItemService(OrderItemRepository orderItemRepository) {

        this.orderItemRepository = orderItemRepository;

    }

    public List<OrderItem> getAllOrders() {

        return orderItemRepository.findAll();

    }

    public Optional<OrderItem> getOrderById(Long id) {

        return orderItemRepository.findById(id);

    }

    public OrderItem createOrder(Cart cart, Transaction transaction) {

        OrderItem orderItem = new OrderItem();

        orderItem.setCart(cart);

        orderItem.setTransaction(transaction);

        return orderItemRepository.save(orderItem);

    }

    public void deleteOrder(Long id) {

        orderItemRepository.deleteById(id);

    }

}
