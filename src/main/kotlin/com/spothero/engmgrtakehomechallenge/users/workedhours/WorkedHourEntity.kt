package com.spothero.engmgrtakehomechallenge.users.workedhours

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "worked_hours")
@IdClass(WorkedHourEntityId::class)
class WorkedHourEntity (
    @Id val userId: Int,
    @Id val date: LocalDate,
    val hours: Float
)

class WorkedHourEntityId(
    val userId: Int = 0,
    val date: LocalDate = LocalDate.now()) : Serializable