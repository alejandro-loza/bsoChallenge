package com.bitso.challenge.repository

import com.bitso.challenge.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

}