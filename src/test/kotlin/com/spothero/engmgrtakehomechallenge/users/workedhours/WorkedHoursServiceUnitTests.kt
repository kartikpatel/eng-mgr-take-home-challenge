package com.spothero.engmgrtakehomechallenge.users.workedhours

import com.spothero.engmgrtakehomechallenge.users.UsersRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.LocalDate
import kotlin.random.Random

@Tag("unit-test")
class WorkedHoursServiceUnitTests {
    private val workedHoursRepository: WorkedHoursRepository = mock()
    private val usersRepository: UsersRepository = mock()
    private val testObject = WorkedHoursService(workedHoursRepository, usersRepository)

    @Test
    fun `when reading worked hours for id then data is converted accurately`() {
        val id = Random.nextInt()
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val workedHourEntities = listOf(
            WorkedHourEntity(userId = id, date = date, hours = hours)
        )
        val expected = listOf(
            WorkedHour(id = id, date = date, hours = hours)
        )

        whenever(workedHoursRepository.findByUserId(id)).thenReturn(workedHourEntities)

        val actual = testObject.readWorkedHoursForId(id)

        actual shouldBe expected
    }

    @Test
    fun `when saving worked hour for valid id then save to repository`() {
        val id = Random.nextInt()
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val workedHourRequest = WorkedHourRequest(date = date, hours = hours)
        val expected = WorkedHourEntity(userId = id, date = date, hours = hours)

        whenever(usersRepository.existsById(id)).thenReturn(true)

        val actual = testObject.createWorkedHourForId(id, workedHourRequest)

        actual shouldBe CreateWorkedHourResult.SUCCESS
        verify(workedHoursRepository, times(1)).save(expected)
    }

    @Test
    fun `when saving worked hour for invalid id then do not save to repository`() {
        val id = Random.nextInt()
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val workedHourRequest = WorkedHourRequest(date = date, hours = hours)

        whenever(usersRepository.existsById(id)).thenReturn(false)

        val actual = testObject.createWorkedHourForId(id, workedHourRequest)

        actual shouldBe CreateWorkedHourResult.USER_ID_NOT_FOUND
        verifyZeroInteractions(workedHoursRepository)
    }
}