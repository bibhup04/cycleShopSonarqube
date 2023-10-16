package com.talentsprint.cycleshop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentsprint.cycleshop.entity.Cart;
import com.talentsprint.cycleshop.entity.Transaction;
import com.talentsprint.cycleshop.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CycleService cycleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderItemService orderItemService;

    public Cart addToCart(int count, long brandId, String username) {

        Cart cart = new Cart();
        cart.setCycle(cycleService.findByIdOrThrow404(brandId));
        cart.setQuantity(count);
        cart.setUser(userService.getByName(username).get());

        return cartRepository.save(cart);
    }

    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    public void removeCartItem(long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    public List<Cart> getCyclesByUserId(long userId) {

        var listFromDB = cartRepository.findByUser_Id(userId);

        var cycleList = new ArrayList<Cart>();

        listFromDB.forEach(cycleList::add);

        return cycleList;
    }

    public void markCartItemsAsBooked(long userId, Transaction transaction) {

        List<Cart> cartItemsToRent = cartRepository.findByUser_IdAndBookedFalse(userId);

        for (Cart cartItem : cartItemsToRent) {

            cartItem.setBooked(true);
            cycleService.borrowCycle(cartItem.getCycle().getId(),
                    cartItem.getQuantity());

            cartRepository.save(cartItem);

        }

    }

    // public void markCartItemsAsBooked(long userId, Transaction transaction) {

    // List<Cart> cartItemsToRent =
    // cartRepository.findByUser_IdAndBookedFalse(userId);

    // for (Cart cartItem : cartItemsToRent) {

    // cartItem.setBooked(true);

    // cycleService.borrowCycle(cartItem.getCycle().getId(),
    // cartItem.getQuantity());

    // cartRepository.save(cartItem);

    // // long lastCartId = cartRepository.findTopByOrderByIdDesc().getId();

    // orderItemService.createOrder(cartItem, transaction);

    // }

    // }

    public void returnCycle(long cartId) {

        Cart cartItem = cartRepository.findById(cartId);

        cartItem.setReturned(true);

        cycleService.returnCycle(cartItem.getCycle().getId(), cartItem.getQuantity());

        cartRepository.save(cartItem);

        if (!checkRemainingCycle(cartItem.getUser().getId())) {

            // transactionService.endTransaction()

        }

    }

    public boolean checkRemainingCycle(long userId) {

        List<Cart> cartItems = cartRepository.findByUser_Id(userId);

        for (Cart cartItem : cartItems) {

            if (!cartItem.isReturned()) {

                return true;

            }

        }

        return false;

    }

    public void reduceCartItem(long cartItemId) {

        Cart cart = cartRepository.findById(cartItemId);
        System.out.println("quantity---------------" + cart.getQuantity());
        cart.setQuantity(cart.getQuantity() - 1);
        System.out.println(cart.getQuantity());
        cartRepository.save(cart);
        if (cart.getQuantity() == 0) {
            removeCartItem(cartItemId);
        }

    }

}
