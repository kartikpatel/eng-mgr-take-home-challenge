package com.spothero.engmgrtakehomechallenge.users.workedhours

import java.time.LocalDate

data class WorkedHourRequest(
    val date: LocalDate,
    val hours: Float
)