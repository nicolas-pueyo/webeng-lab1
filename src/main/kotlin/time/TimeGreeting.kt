package es.unizar.webeng.hello.time

import java.time.LocalTime
import org.springframework.stereotype.Service

@Service
class TimeGreetingService(private val clock: AppClock) {
    fun greet(name: String?): String {
        val who = (name?.takeIf { it.isNotBlank() } ?: "jefe").trim()
        val time: LocalTime = clock.now().toLocalTime()
        val prefix = when (time) {
            in MORNING_START..MORNING_END -> "Buenos días"
            in AFTERNOON_START..AFTERNOON_END -> "Buenas tardes"
            else -> "Buenas noches"
        }
        return "$prefix, $who"
    }

    companion object {
        private val MORNING_START = LocalTime.of(5,0)
        private val MORNING_END = LocalTime.of(12,59,59)
        private val AFTERNOON_START = LocalTime.of(13,0)
        private val AFTERNOON_END = LocalTime.of(19,59,59)
    }
}