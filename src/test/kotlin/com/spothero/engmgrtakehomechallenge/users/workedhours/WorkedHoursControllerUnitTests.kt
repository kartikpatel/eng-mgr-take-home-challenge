package com.spothero.engmgrtakehomechallenge.users.workedhours

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("unit-test")
class WorkedHoursControllerUnitTests {
    private val testObject = WorkedHoursController()

    @Test
    fun `when get worked hours then worked hours for id are returned`() {
        val actual = testObject.getWorkedHours(1)

        actual shouldBe listOf()
    }
}