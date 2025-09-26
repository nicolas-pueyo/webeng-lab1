package es.unizar.webeng.hello.time

import java.time.LocalDateTime
import java.time.ZoneId
import org.springframework.stereotype.Component


interface AppClock {
    fun now(): LocalDateTime
}

@Component
class SystemClock : AppClock {
    override fun now(): LocalDateTime = LocalDateTime.now(ZoneId.of("Europe/Madrid"))
}

