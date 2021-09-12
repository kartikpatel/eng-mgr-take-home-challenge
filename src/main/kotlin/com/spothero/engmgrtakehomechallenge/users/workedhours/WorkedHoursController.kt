package com.spothero.engmgrtakehomechallenge.users.workedhours

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{id}/worked_hours")
class WorkedHoursController(private val workedHoursService: WorkedHoursService) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getWorkedHours(@PathVariable id: Int): List<WorkedHour> = workedHoursService.readWorkedHoursForId(id)
}