package com.spothero.engmgrtakehomechallenge.users.workedhours

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
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

    @Test
    fun `when submitting worked hour with successful result then HTTP status is created`() {
        val id = Random.nextInt()
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val workedHourRequest = WorkedHourRequest(date = date, hours = hours)

        whenever(workedHoursService.createWorkedHourForId(id, workedHourRequest)).thenReturn(CreateWorkedHourResult.SUCCESS)

        val actual = testObject.postWorkedHour(id, workedHourRequest)

        actual.statusCode shouldBe HttpStatus.CREATED
    }

    @Test
    fun `when submitting worked hour with user id not found result then HTTP status is not found`() {
        val id = Random.nextInt()
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val workedHourRequest = WorkedHourRequest(date = date, hours = hours)

        whenever(workedHoursService.createWorkedHourForId(id, workedHourRequest)).thenReturn(CreateWorkedHourResult.USER_ID_NOT_FOUND)

        val actual = testObject.postWorkedHour(id, workedHourRequest)

        actual.statusCode shouldBe HttpStatus.NOT_FOUND
    }
}