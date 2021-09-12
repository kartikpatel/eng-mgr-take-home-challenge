package com.spothero.engmgrtakehomechallenge.users.workedhours

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkedHoursRepository: JpaRepository<WorkedHourEntity, WorkedHourEntityId> {
    fun findByUserId(userId: Int): List<WorkedHourEntity>
}