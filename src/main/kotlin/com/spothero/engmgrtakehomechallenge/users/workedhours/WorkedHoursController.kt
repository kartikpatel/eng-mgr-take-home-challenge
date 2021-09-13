package com.spothero.engmgrtakehomechallenge.users.workedhours

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users/{id}/worked_hours")
class WorkedHoursController(private val workedHoursService: WorkedHoursService) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getWorkedHours(@PathVariable id: Int): List<WorkedHour> = workedHoursService.readWorkedHoursForId(id)

    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "created worked hour record"),
        ApiResponse(responseCode = "404", description = "user id not found")
    ])
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postWorkedHour(@PathVariable id: Int, @RequestBody workedHourRequest: WorkedHourRequest): ResponseEntity<Unit> {
        return when (workedHoursService.createWorkedHourForId(id, workedHourRequest)) {
            CreateWorkedHourResult.SUCCESS -> ResponseEntity(HttpStatus.CREATED)
            CreateWorkedHourResult.USER_ID_NOT_FOUND -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}