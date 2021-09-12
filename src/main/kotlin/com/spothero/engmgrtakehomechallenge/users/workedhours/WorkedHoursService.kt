package com.spothero.engmgrtakehomechallenge.users.workedhours

import com.spothero.engmgrtakehomechallenge.users.UsersRepository
import org.springframework.stereotype.Service

@Service
class WorkedHoursService(
    private val workedHoursRepository: WorkedHoursRepository,
    private val usersRepository: UsersRepository) {

    fun readWorkedHoursForId(id: Int): List<WorkedHour> {
        return workedHoursRepository
            .findByUserId(id)
            .map { WorkedHour(id = it.userId, date = it.date, hours = it.hours) }
    }

    fun createWorkedHourForId(id: Int, workedHourRequest: WorkedHourRequest): CreateWorkedHourResult {
        val workedHourEntity = WorkedHourEntity(userId = id, date = workedHourRequest.date, hours = workedHourRequest.hours)

        if (!usersRepository.existsById(id)) {
            return CreateWorkedHourResult.USER_ID_NOT_FOUND
        }

        workedHoursRepository.save(workedHourEntity)
        return CreateWorkedHourResult.SUCCESS
    }
}

enum class CreateWorkedHourResult {
    SUCCESS, USER_ID_NOT_FOUND
}