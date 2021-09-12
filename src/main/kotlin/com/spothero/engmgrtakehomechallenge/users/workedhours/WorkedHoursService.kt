package com.spothero.engmgrtakehomechallenge.users.workedhours

import org.springframework.stereotype.Service

@Service
class WorkedHoursService(private val workedHoursRepository: WorkedHoursRepository) {
    fun readWorkedHoursForId(id: Int): List<WorkedHour> {
        return workedHoursRepository
            .findByUserId(id)
            .map { WorkedHour(id = it.userId, date = it.date, hours = it.hours) }
    }

    fun createWorkedHourForId(id: Int, workedHourRequest: WorkedHourRequest) {
        val workedHourEntity = WorkedHourEntity(userId = id, date = workedHourRequest.date, hours = workedHourRequest.hours)
        workedHoursRepository.save(workedHourEntity)
    }
}