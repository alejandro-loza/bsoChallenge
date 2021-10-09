package com.bitso.challenge.model.ram;

import com.bitso.challenge.entity.Currency;
import com.bitso.challenge.entity.Order;
import com.bitso.challenge.model.OrderModel;
import com.bitso.challenge.validation.OrderCmd;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * RAM-based implementation of orders.
 */
public class OrderModelImpl implements OrderModel {

    private HashMap<Long, OrderRam> orders = new HashMap<>(100);
    private AtomicLong seq = new AtomicLong();

    @Override
    public Order submit(OrderCmd cmd) throws IllegalArgumentException {
        OrderRam order = new OrderRam();
        order.setUserId(cmd.getUserId());
        order.setMajor(Currency.valueOf(cmd.getMajor()));
        order.setMinor(Currency.valueOf(cmd.getMinor()));
        order.setAmount(cmd.getAmount());
        order.setPrice(cmd.getPrice());
        order.setCreated(new Date());
        order.setStatus(OrderRam.Status.active);
        insert(order);
        return order;
    }

    public long insert(Order order) {
        //TODO validate
        order.setId(seq.incrementAndGet());
        orders.put(order.getId(), (OrderRam) order);
        return order.getId();
    }

    @Override
    public Optional<Order> get(long id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> findOrdersForUser(long userId, Order.Status status, Currency major, Currency minor) {
        return orders.values().stream()
            .filter(o -> o.getUserId() == userId)
            .filter(o -> status == null || status == o.getStatus())
            .filter(o -> major == o.getMajor() && minor == o.getMinor())
            .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrdersForBook(Currency major, Currency minor) {
        return orders.values().stream()
             .filter(o -> major == o.getMajor() && minor == o.getMinor())
             .collect(Collectors.toList());
    }
}
