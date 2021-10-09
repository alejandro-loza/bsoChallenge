package com.bitso.challenge.service;

import com.bitso.challenge.entity.Currency;
import com.bitso.challenge.entity.Order;
import com.bitso.challenge.model.OrderModel;
import com.bitso.challenge.model.OrderModelDBImpl;
import com.bitso.challenge.model.UserModel;
import com.bitso.challenge.model.ram.UserModelImpl;
import com.bitso.challenge.validation.OrderCmd;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST endpoint to submit and query orders.
 */
@RestController("orders")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private OrderModel orderModel;

    private UserModel userModel;

    public OrderController() {
        this.orderModel = new OrderModelDBImpl();
        this.userModel =  new UserModelImpl();
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Optional<Order> get(@PathVariable long id) {
        Optional<Order> order = orderModel.get(id);
        order.ifPresentOrElse(
                o -> log.debug("get {}: {}", id, o),
                () -> log.debug("get {}: null", id));
        return order;
    }

    @RequestMapping("/submit") @PostMapping
    public Order submit(OrderCmd cmd) {
        log.debug("Submitting order {}", cmd);
        return orderModel.submit(cmd);
    }

    @RequestMapping("/book/{major}/{minor}")
    public List<Order> findOrdersForBook(@PathVariable String major,
                                         @PathVariable String minor) {
        //TODO validate currencies
        var maj = Currency.valueOf(major);
        var min = Currency.valueOf(minor);
        return orderModel.findOrdersForBook(maj, min);
    }

    @RequestMapping("/query/{userId}/{status}/{major}/{minor}")
    public List<Order> getBy(@PathVariable long userId,
                             @PathVariable String status,
                             @PathVariable String major,
                             @PathVariable String minor) {
        Order.Status st = status == null || status.isEmpty() ? null : Order.Status.valueOf(status);
        var maj = Currency.valueOf(major);
        var min = Currency.valueOf(minor);
        List<Order> r = orderModel.findOrdersForUser(userId, st, maj, min);
        log.debug("Query {}/{}/{}/{} returns {} orders", userId, st, maj, min, r.size());
        return r;
    }
}
