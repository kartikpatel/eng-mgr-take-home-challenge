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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorkedHourEntity

        if (userId != other.userId) return false
        if (date != other.date) return false
        if (hours != other.hours) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + date.hashCode()
        result = 31 * result + hours.hashCode()
        return result
    }
}

class WorkedHourEntityId(
    val userId: Int = 0,
    val date: LocalDate = LocalDate.now()) : Serializable