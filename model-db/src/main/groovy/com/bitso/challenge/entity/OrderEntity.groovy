package com.bitso.challenge.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class OrderEntity extends Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id
    UserEntity user
}
