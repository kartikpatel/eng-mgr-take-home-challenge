package com.spothero.engmgrtakehomechallenge.users.workedhours

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDate
import kotlin.random.Random

@Tag("unit-test")
class WorkedHoursServiceUnitTests {
    private val workedHoursRepository: WorkedHoursRepository = mock()
    private val testObject = WorkedHoursService(workedHoursRepository)

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

        whenever(workedHoursRepository.findAll()).thenReturn(workedHourEntities)

        val actual = testObject.readWorkedHoursForId(id)

        actual shouldBe expected
    }
}