package com.bitso.challenge.repository

import com.bitso.challenge.entity.Currency
import com.bitso.challenge.entity.Order
import com.bitso.challenge.entity.OrderEntity
import com.bitso.challenge.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository extends JpaRepository<OrderEntity, Long>{
   List<OrderEntity> findAllByUserAndStatusAndMinorAndMajor(UserEntity user, Order.Status status, Currency major, Currency minor)
   List<OrderEntity> findAllByMinorAndMajor( Currency major, Currency minor)

}