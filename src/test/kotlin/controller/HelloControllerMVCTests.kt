package es.unizar.webeng.hello.controller

import es.unizar.webeng.hello.time.TimeGreetingService
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.Mockito

/**
 * MVC tests just for HelloController,
 * loading a slice of the application context
 * and testing valid returns.
 */
@WebMvcTest(HelloController::class)
class HelloControllerMVCTests {
    @Value("\${app.message:Welcome to the Modern Web App!}")
    private lateinit var message: String

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return home page with default message`() {
        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("welcome"))
            .andExpect(model().attribute("message", equalTo(message)))
            .andExpect(model().attribute("name", equalTo("")))
    }
    
    @Test
    fun `should return home page with personalized message`() {
        mockMvc.perform(get("/").param("name", "Developer"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("welcome"))
            .andExpect(model().attribute("message", equalTo("Hello, Developer!")))
            .andExpect(model().attribute("name", equalTo("Developer")))
    }
}

/**
 * MVC tests just for HelloApiController,
 * loading a slice of the application context
 * and testing valid returns.
 */
@WebMvcTest(HelloApiController::class)
class HelloApiControllerMVCTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var timeGreetingService: TimeGreetingService

    @Test
    fun `should return API response as JSON`() {

    /** Fixed message, we only test the controller call+return, 
    *   the service is tested separately in unit tests.
    */
    Mockito.`when`(timeGreetingService.greet("Test")).thenReturn("Good afternoon, Test!")

        mockMvc.perform(get("/api/hello").param("name", "Test"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message", anyOf (
                equalTo("Good morning, Test!"),
                equalTo("Good afternoon, Test!"),
                equalTo("Good evening, Test!")
            )))
            .andExpect(jsonPath("$.timestamp").exists())
    }
}

