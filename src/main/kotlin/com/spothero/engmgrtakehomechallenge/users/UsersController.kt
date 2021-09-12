package com.spothero.engmgrtakehomechallenge.users

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(private val usersService: UsersService) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers(): List<User> = usersService.readUsers()
}