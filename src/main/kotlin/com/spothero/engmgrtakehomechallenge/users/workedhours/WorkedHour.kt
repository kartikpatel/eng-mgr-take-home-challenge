package com.spothero.engmgrtakehomechallenge.users.workedhours

import java.time.LocalDate

data class WorkedHour (
    val id: Int,
    val date: LocalDate,
    val hours: Float
)