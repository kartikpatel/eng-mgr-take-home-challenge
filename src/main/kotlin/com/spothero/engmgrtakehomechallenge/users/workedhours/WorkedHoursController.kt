package com.spothero.engmgrtakehomechallenge.users.workedhours

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users/{id}/worked_hours")
class WorkedHoursController(private val workedHoursService: WorkedHoursService) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getWorkedHours(@PathVariable id: Int): List<WorkedHour> = workedHoursService.readWorkedHoursForId(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postWorkedHour(@PathVariable id: Int, @RequestBody workedHourRequest: WorkedHourRequest) {
        workedHoursService.createWorkedHourForId(id, workedHourRequest)
    }
}