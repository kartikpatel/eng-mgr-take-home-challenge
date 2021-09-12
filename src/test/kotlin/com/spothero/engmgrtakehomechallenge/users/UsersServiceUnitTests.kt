package com.spothero.engmgrtakehomechallenge.users

import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.random.Random

class UsersServiceUnitTests {
    private val usersRepository: UsersRepository = mock()
    private val testObject = UsersService(usersRepository)

    @Test
    fun `when reading users then data is converted accuretly`() {
        val id = Random.nextInt()
        val firstName = Random.nextBytes(10).toString()
        val lastName = Random.nextBytes(10).toString()
        val email = Random.nextBytes(10).toString()
        val active = Random.nextBoolean()

        val userEntities = listOf(
            UserEntity(id = id, firstName = firstName, lastName = lastName, email = email, active = active)
        )
        val expected = listOf(
            User(id = id, firstName = firstName, lastName = lastName, email = email)
        )

        whenever(usersRepository.findAll()).thenReturn(userEntities)

        val actual = testObject.readUsers()

        actual shouldBe expected
    }
}