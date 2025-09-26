package es.unizar.webeng.hello.controller

import es.unizar.webeng.hello.time.AppClock
import es.unizar.webeng.hello.time.TimeGreetingService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDateTime

// FakeClock simula la hora que queramos
private class FakeClock(private val fixed: LocalDateTime) : AppClock {
    override fun now(): LocalDateTime = fixed
}

class HelloApiControllerUnitTest {

    @Test
    fun `should return API response with Buenos días for morning`() {
        val clock = FakeClock(LocalDateTime.parse("2025-01-01T08:30:00"))  // Hora 8:30 AM
        val service = TimeGreetingService(clock)

        val apiController = HelloApiController(service)
        val response = apiController.helloApi(null)

        assertThat(response.message).startsWith("Buenos días")
        assertThat(response.timestamp).isNotBlank()
    }

    @Test
    fun `should return API response with Buenas tardes for afternoon`() {
        val clock = FakeClock(LocalDateTime.parse("2025-01-01T15:00:00"))  // Hora 3:00 PM
        val service = TimeGreetingService(clock)

        val apiController = HelloApiController(service)
        val response = apiController.helloApi(null)

        assertThat(response.message).startsWith("Buenas tardes")
        assertThat(response.timestamp).isNotBlank()
    }

    @Test
    fun `should return API response with Buenas noches for night`() {
        val clock = FakeClock(LocalDateTime.parse("2025-01-01T22:00:00"))  // Hora 10:00 PM
        val service = TimeGreetingService(clock)

        val apiController = HelloApiController(service)
        val response = apiController.helloApi(null)

        assertThat(response.message).startsWith("Buenas noches")
        assertThat(response.timestamp).isNotBlank()
    }
}
