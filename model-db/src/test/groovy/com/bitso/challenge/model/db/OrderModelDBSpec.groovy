package com.bitso.challenge.model.db

import com.bitso.challenge.entity.Order
import com.bitso.challenge.model.db.entity.OrderEntity
import com.bitso.challenge.validation.OrderCmd
import spock.lang.Specification

/**
 * Test the in-memory implementation of the OrderModel.
 */
class OrderModelDBSpec extends Specification {

    def model = new OrderModelDBImpl()

    void "insert, then query"() {

        expect: "orders by user work"
        model.submit(new OrderCmd(userId: 1, major: 'btc', minor: 'mxn',
                amount:1.0, price: 350_000.00)).id == 0

    }
}
