package es.unizar.webeng.hello.controller

import es.unizar.webeng.hello.time.AppClock
import es.unizar.webeng.hello.time.TimeGreetingService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDateTime

/**
 * Fake clock for testing, returns a fixed, chosen time.
 */
private class FakeClock(private val fixed: LocalDateTime) : AppClock {
    override fun now(): LocalDateTime = fixed
}

/**
 *  Unit tests for HelloApiController 
*/
class HelloApiControllerUnitTest {

    @Test
    fun `should return API response with Good morning for morning`() {
        //Test for 8:30 AM
        val clock = FakeClock(LocalDateTime.parse("2025-01-01T08:30:00"))
        val service = TimeGreetingService(clock)

        val apiController = HelloApiController(service)
        val response = apiController.helloApi(null)

        assertThat(response.message).startsWith("Good morning")
        assertThat(response.timestamp).isNotBlank()
    }

    @Test
    fun `should return API response with Good afternoon for afternoon`() {
        //Test for 3:00 PM
        val clock = FakeClock(LocalDateTime.parse("2025-01-01T15:00:00"))  // Hora 3:00 PM
        val service = TimeGreetingService(clock)

        val apiController = HelloApiController(service)
        val response = apiController.helloApi(null)

        assertThat(response.message).startsWith("Good afternoon")
        assertThat(response.timestamp).isNotBlank()
    }

    @Test
    fun `should return API response with Good evening for night`() {
        //Test for 10:00 PM
        val clock = FakeClock(LocalDateTime.parse("2025-01-01T22:00:00"))
        val service = TimeGreetingService(clock)

        val apiController = HelloApiController(service)
        val response = apiController.helloApi(null)

        assertThat(response.message).startsWith("Good evening")
        assertThat(response.timestamp).isNotBlank()
    }
}
