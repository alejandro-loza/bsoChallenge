package com.bitso.challenge.entity

import com.bitso.challenge.entity.Order

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserEntity extends Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id
}