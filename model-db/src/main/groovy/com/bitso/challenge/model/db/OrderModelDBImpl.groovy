package com.bitso.challenge.model.db

import com.bitso.challenge.entity.Currency
import com.bitso.challenge.entity.Order
import com.bitso.challenge.model.OrderModel


class OrderModelDBImpl implements OrderModel{

    @Override
    long submit(Order order) {
        return 666
    }

    @Override
    Optional<Order> get(long id) {
        return null
    }

    @Override
    List<Order> findOrdersForUser(long userId, Order.Status status, Currency major, Currency minor) {
        return null
    }

    @Override
    List<Order> findOrdersForBook(Currency major, Currency minor) {
        return null
    }

    @Override
    long insert(Order order) {
        return 0
    }
}
