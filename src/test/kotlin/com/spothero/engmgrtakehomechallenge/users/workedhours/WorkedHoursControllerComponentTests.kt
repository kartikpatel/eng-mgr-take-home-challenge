package com.spothero.engmgrtakehomechallenge.users.workedhours

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.LocalDate
import kotlin.random.Random


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@Sql("classpath:schema.sql", "classpath:data.sql")
@Tag("component-test")
class WorkedHoursControllerComponentTests {

    companion object {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:13-alpine")

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgreSQLContainer::getUsername)
            registry.add("spring.datasource.password", postgreSQLContainer::getPassword)
        }
    }

    @Test
    fun getWorkedHours(@Autowired restTemplate: TestRestTemplate) {
        val id = 1

        val response = restTemplate.getForEntity("/v1/users/${id}/worked_hours", Array<WorkedHour>::class.java)

        response.statusCode shouldBe HttpStatus.OK
        response.body?.size shouldBe 6
        response.body?.all { it.id == id }?.shouldBeTrue()
    }

    @Test
    fun postWorkedHourWithValidRequest(@Autowired restTemplate: TestRestTemplate) {
        val id = 1
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val request = HttpEntity<WorkedHourRequest>(WorkedHourRequest(date = date, hours = hours))

        val response = restTemplate.postForEntity("/v1/users/${id}/worked_hours", request, List::class.java)

        response.statusCode shouldBe HttpStatus.CREATED
    }

    @Test
    fun postWorkedHourWithInvalidUserId(@Autowired restTemplate: TestRestTemplate) {
        val id = Int.MAX_VALUE
        val date = LocalDate.now()
        val hours = Random.nextFloat()

        val request = HttpEntity<WorkedHourRequest>(WorkedHourRequest(date = date, hours = hours))

        val response = restTemplate.postForEntity("/v1/users/${id}/worked_hours", request, List::class.java)

        response.statusCode shouldBe HttpStatus.NOT_FOUND
    }
}