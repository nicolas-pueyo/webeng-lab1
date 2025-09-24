package es.unizar.webeng.hello.time

import java.time.LocalDateTime
import java.time.ZoneId
import org.springframework.stereotype.Component

/** Abstracción para obtener la hora actual (testable). */
interface AppClock {
    fun now(): LocalDateTime   // <- ¡IMPORTANTE! Devuelve LocalDateTime, NO Unit
}

/** Implementación real: hora del sistema (fijamos zona a Europe/Madrid para coherencia). */
@Component
class SystemClock : AppClock {
    override fun now(): LocalDateTime = LocalDateTime.now(ZoneId.of("Europe/Madrid"))
}
