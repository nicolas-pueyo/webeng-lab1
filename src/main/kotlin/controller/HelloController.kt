package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import es.unizar.webeng.hello.time.TimeGreetingService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Operation

@Controller
class HelloController(
    @param:Value("\${app.message:Hello World}") 
    private val message: String
) {
    
    @GetMapping("/")
    fun welcome(
        model: Model,
        @RequestParam(defaultValue = "") name: String
    ): String {
        val greeting = if (name.isNotBlank()) "Hello, $name!" else message
        model.addAttribute("message", greeting)
        model.addAttribute("name", name)
        return "welcome"
    }
}

@RestController
class HelloApiController(
    private val timeGreetingService: TimeGreetingService
) {
    
    @Operation(summary = "Time-based greeting", description = "Returns a greeting based on current time.")
    @GetMapping("/api/hello", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloApi(
        @Parameter(description = "Optional name to greet")
        @RequestParam(required = false) name: String?
    ): HelloResponse {
        val msg = timeGreetingService.greet(name)
        return HelloResponse(message = msg, timestamp = java.time.Instant.now().toString())
    }
}
