package com.bitso.challenge.model.ram

import com.bitso.challenge.entity.Currency
import com.bitso.challenge.validation.OrderCmd
import spock.lang.Specification

/**
 * Test the in-memory implementation of the OrderModel.
 */
class OrderModelSpec extends Specification {

    def model = new OrderModelImpl()

    void "insert, then query"() {
        model.submit(new OrderCmd(userId: 1, major: 'btc', minor: 'mxn',
                amount:1.0, price: 350_000.00))
        model.submit(new OrderCmd(userId: 2, major: 'btc', minor: 'mxn',
                amount:2.0, price: 330_000.00))
        model.submit(new OrderCmd(userId: 3, major: 'eth', minor: 'mxn',
                amount:0.00001, price: 12_000.00))
        expect: "orders by user work"
            model.findOrdersForUser(1, OrderRam.Status.active, Currency.btc, Currency.mxn).size() == 1
            model.findOrdersForUser(2, OrderRam.Status.active, Currency.btc, Currency.mxn).size() == 1
            model.findOrdersForUser(3, OrderRam.Status.active, Currency.eth, Currency.mxn).size() == 1
            model.findOrdersForUser(3, OrderRam.Status.active, Currency.ltc, Currency.mxn).empty
            model.findOrdersForUser(3, OrderRam.Status.complete, Currency.eth, Currency.mxn).empty
        and: "orders by book work"
            model.findOrdersForBook(Currency.btc, Currency.mxn).size() == 2
            model.findOrdersForBook(Currency.eth, Currency.mxn).size() == 1
            model.findOrdersForBook(Currency.ltc, Currency.mxn).empty
    }
}
