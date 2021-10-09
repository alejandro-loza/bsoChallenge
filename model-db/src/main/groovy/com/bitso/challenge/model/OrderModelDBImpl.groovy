package com.bitso.challenge.model

import com.bitso.challenge.entity.Currency
import com.bitso.challenge.entity.Order
import com.bitso.challenge.entity.OrderEntity
import com.bitso.challenge.entity.UserEntity
import com.bitso.challenge.repository.OrderRepository
import com.bitso.challenge.repository.UserEntityRepository
import com.bitso.challenge.validation.OrderCmd
import javassist.NotFoundException
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service
class OrderModelDBImpl implements OrderModel{

    @Inject
    OrderRepository orderRepository

    @Inject
    UserEntityRepository userRepository

    @Override
    Order submit(OrderCmd cmd) throws IllegalArgumentException, NotFoundException {
        OrderEntity order = new OrderEntity()
        order.with {
            user = findUser(cmd.userId)
            major = Currency.valueOf(cmd.major)
            minor = Currency.valueOf(cmd.minor)
            amount = cmd.amount
            price = cmd.price
            created = new Date()
            status = Order.Status.active
        }
        insert(order)
        order
     }

    @Override
    Optional<Order> get(long id) {
        orderRepository.findById(id)
    }

    @Override
    List<Order> findOrdersForUser(long userId, Order.Status status, Currency major, Currency minor) {
        return orderRepository
                .findAllByUserAndStatusAndMinorAndMajor(findUser(userId), status, major, minor)
    }

    @Override
    List<Order> findOrdersForBook(Currency major, Currency minor) {
        return orderRepository.findAllByMinorAndMajor(major, minor)
    }

    @Override
    long insert(Order order) {
        if(order instanceof OrderEntity){
           return orderRepository.save(order).id
        }
        return 0
    }

    private UserEntity findUser(long userId) {
        userRepository.findById(userId)
                .orElseThrow({ -> new NotFoundException('user.notFound') })
    }
}
