package com.spothero.engmgrtakehomechallenge.users.workedhours

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDate
import kotlin.random.Random

@Tag("unit-test")
class WorkedHoursControllerUnitTests {
    private val workedHoursService: WorkedHoursService = mock()
    private val testObject = WorkedHoursController(workedHoursService)

    @Test
    fun `when get worked hours then worked hours for id are returned`() {
        val id = Random.nextInt()
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val workedHours = listOf(
            WorkedHour(id = id, date = date, hours = hours)
        )

        whenever(workedHoursService.readWorkedHoursForId(id)).thenReturn(workedHours)

        val actual = testObject.getWorkedHours(id)

        actual shouldBe workedHours
    }
}