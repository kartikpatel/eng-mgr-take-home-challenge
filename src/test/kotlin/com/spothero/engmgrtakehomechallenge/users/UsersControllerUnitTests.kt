package com.spothero.engmgrtakehomechallenge.users

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.random.Random

@Tag("unit-test")
class UsersControllerUnitTests {
    private val usersService: UsersService = mock()
    private val testObject = UsersController(usersService)

    @Test
    fun `when get users then all users from service are returned`() {
        val id = Random.nextInt()
        val firstName = Random.nextBytes(10).toString()
        val lastName = Random.nextBytes(10).toString()
        val email = Random.nextBytes(10).toString()

        val users = listOf(
            User(id = id, firstName = firstName, lastName = lastName, email = email)
        )

        whenever(usersService.readUsers()).thenReturn(users)

        val actual = testObject.getUsers()

        actual shouldBe users
    }
}