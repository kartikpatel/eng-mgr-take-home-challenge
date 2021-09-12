package com.spothero.engmgrtakehomechallenge.users

import org.springframework.stereotype.Service

@Service
class UsersService(private val usersRepository: UsersRepository) {
    fun readUsers(): List<User> {
        return usersRepository
            .findByActiveIsTrue()
            .map { User(id = it.id, firstName = it.firstName, lastName = it.lastName, email = it.email) }
    }
}