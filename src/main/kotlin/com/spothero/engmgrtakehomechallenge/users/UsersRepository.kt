package com.spothero.engmgrtakehomechallenge.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository: JpaRepository<UserEntity, Int> {
    fun findByActiveIsTrue(): List<UserEntity>
}