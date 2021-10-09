package com.bitso.challenge.model;

import com.bitso.challenge.entity.Currency;
import com.bitso.challenge.entity.Order;
import com.bitso.challenge.validation.OrderCmd;

import java.util.List;
import java.util.Optional;

/**
 * Model to handle orders.
 */
public interface OrderModel {

    /** Inserts an order, assigning a new ID, a status of 'active', and setting its creation date. */
    Order submit(OrderCmd order) throws IllegalArgumentException;
    Optional<Order> get(long id);
    /** Fetch the orders for the specified userId.
     * @param userId mandatory ID of the user whose orders you want.
     * @param status optional status of the orders for the user.
     * @param major optional currency of the orders for the user. */
    List<Order> findOrdersForUser(long userId, Order.Status status, Currency major, Currency minor);
    List<Order> findOrdersForBook(Currency major, Currency minor);

    /** Inserts an order, assigning a new ID but leaving everything else as is. */
    long insert(Order order);
}
