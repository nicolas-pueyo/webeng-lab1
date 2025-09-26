# Lab 1 Git Race -- Project Report

## Description of Changes
Se han añadido:
- Modo oscuro mediante alteración de propiedades de Bootstrap, añadiendo un botón para cambiar entre modo claro y modo oscuro con HTML y JS. Conseguido extrayendo el valor de la propiedad "data-bs-theme" del tag HTML y dependiendo del valor cambiarlo al otro.
- Documentación Swagger añadiendo la da dependencia. La elección concreta de la librería se debe a que probé varias ("sprindoc-swagger-ui", por ejemplo), pero esta fue la única que funcionó.
- Saludo al usuario basado en la hora del día.

En cuanto al desarrollo de pruebas, he optado por testear esta funcionalidad con tests unitarios para el controlador que prueba que en una hora de cada franja devuelve el saludo esperado (esto se ha hecho aprovechando la dependencia de un reloj externo, inyectando un reloj falso al que se le puede poner la hora que sea), por adaptar un poco los tests de MVC (separando en dos clases para utilizar un MockBean para mockear el servicio y que devuelva un mensaje fijo, ya que aquí estamos probando la llamada+respuesta del controlador con el correcto montaje del mensaje y el horario se prueba en los unitarios anteriormente mencionados), y modificar un poco los tests de integración para esperar respuestas HTTP de la API.


Antes de commitear este REPORT.md, el [repositorio de GitHub](https://github.com/nicolas-pueyo/webeng-lab1)(que ha permanecido privado hasta la entrega de la práctica) marca que he añadido 177 líneas y eliminado 27, lo que deja 150 líneas finales modificadas por mi parte.

## Technical Decisions
Las dos primeras features tienen pocas decisiones técnicas detrás. El modo oscuro se hace mediante la propiedad de bootstrap por usar una funcionalidad moderna de un framewor de CSS utilizado en la actualidad, mejor que modificar manualmente las propiedades de los elementos HTML, además de facilitar mucho la automatización de esto con un botón. En el caso de Swagger no hay ninguna decisión técnica de peso por ser sólo la inclusión de una dependencia. La elección concreta de la librería se debe a que probé varias ("sprindoc-swagger-ui", por ejemplo), pero esta fue la única que funcionó.

La tercera funcionalidad, el saludo basado en hora del día, sí que tiene más decisiones técnicas detrás. Primero, la estructura de la funcionalidad. He optado por separar las responsabilidades entre un servicio llamado TimeGreetingService que utiliza un reloj y según la hora devuelve un saludo diferente, y hacer que el controlador HelloApiController con el mapping "/api/hello" llame a ese servicio con el nombre que se le proporciona por la query. La principal razón para separarlos es la modularidad y la separación de responsabilidades, así si necesitara en otro momento un saludo basado en tiempo (es poco probable pero nunca se sabe) se podría reutilizar el servicio.

En cuanto al desarrollo de pruebas, he optado por testear esta funcionalidad con tests unitarios para el controlador que prueba que en una hora de cada franja devuelve el saludo esperado (esto se ha hecho aprovechando la dependencia de un reloj externo, inyectando un reloj falso al que se le puede poner la hora que sea), por adaptar un poco los tests de MVC (separando en dos clases para utilizar un MockBean para mockear el servicio y que devuelva un mensaje fijo, ya que aquí estamos probando la llamada+respuesta del controlador con el correcto montaje del mensaje y el horario se prueba en los unitarios anteriormente mencionados), y modificar un poco los tests de integración para esperar respuestas HTTP de la API.

## Learning Outcomes
Partiendo de una base nula de trabajo con Spring Boot y Kotlin, y con un trabajo mínimo con Gradle en Ingeniería de Software (aunque con buena base de JavaScript), esta práctica me ha servido para familiarizarme con este entorno web moderno y apreciar sus ventajas, como el automontaje de la apliación y lo sencillo que es ejecutar los tests.

Algunos aprendizajes concretos han sido la funcionalidad de la propiedad "data-bs-theme" para modificación de apariencia clara/oscura sencilla (nunca había trabajado con Bootstrap) o el uso de Mocks y automatización en tests, pues hasta ahora solo había desarrollado tests de integración y generalmente por interacción con UI.

También, incidentalmente, he aprendido algo de Kotlin.

## AI Disclosure
### AI Tools Used
Por la nula experiencia previa con este entorno comentada anteriormente, he usado como guías y ayudas:
- ChatGPT 5
- Claude Sonnet 3.7

Sonnet 3.7 ha sido usado solo dentro del IDE con Copilot ocasionalmente y el LLM principal ha sido ChatGPT 5.

### AI-Assisted Work
La IA ha cumplido dos funciones en el desarrollo de la práctica: guía y generación de código.
La función de guía se debe al recurrente tema de la escasa experiencia previa con el entorno, por lo que recurrí a GPT para entender la construcción del proyecto (dependencias de Spring Boot, cómo están montados los controladores y los tests, a quién pertenece cada responsabilidad...). Posteriormente, también pedí a la IA generación de esqueletos de clases y explicación de cómo hacer en un estándar más o menos moderno de Kotlin alguna cosa, por ejemplo el pattern matching de TimeGreetingService para decidir qué saludo dar.

También fue útil en la inclusión de un DTO como respuesta, pues fue ChatGPT quien me lo sugirió y me mostró como implementarlo.

También ha asistido en la generación de tests (uno de los puntos fuertes que se remarcan ultimamente acerca de la IA, su capacidad para desarrollar y automatizar pruebas). Aunque algunos no los hace del todo bien, todo se arregla revisando lo que hace. De hecho esto ha sido muy necesario, pues hablando de estándares modernos los modelos no suelen estar tan entrenados como en algo que está bien establecido ya y suele hacer cosas raras, por eso ha sido importante también guiar a la IA con cosas que hace mal, como tests inútiles que genera o lógica que no tiene sentido. Esta práctica ha sido un gran ejercicio de revisión y pulimento de trabajo de IA. El único fichero que es generado enteramente por IA es HelloApiControllerUnitTest.kt, ya que le pedí unos tests unitarios para el endpoint+servicio y me gustó lo que generó, además de que funcionaban correctamente.

Entre esto y todas las revisiones que he hecho del trabajo generado por IA, estimo la práctica en entre un 65 y un 70% hecho o fuertemente auditado por mi y un 30-35% generado por IA.

### Original Work
Incluiré en este punto también el trabajo que, aunque se le ha pedido asistencia a la IA, esta ha sido mayormente inútil y ha requerido revisión y reestructuración manual, porque lo considero trabajo propio en el que he invertido tiempo.

El trabajo original desarrollado ha sido (aparte del estudio independiente del proyecto sin IA):
- Desarrollo de modo oscuro y Swagger, dado que se encuentran buscando un poco en la documentación de Bootstrap y mirando librerías de Swagger (aunque para esto reconozco la ayuda del compañero Curro Valero, que después de varias pruebas fallidas me dijo que librería usó).
- Desarrollo de interfaces como AppClock o SystemClock, con un poco de documentación y experiencia previa de Java
- Desarrollo de TimeGreetingService y reestructuración del controlador en el API endpoint concreto (en parte, pues también pedí sugerencias de mejora una vez hecho a la IA, como el pattern matching o el companion object).
- Revisión, prueba y correcciones de tests. Los tests unitarios de HelloApiController si que fueron generados enteramente por IA, pero otros le resulta más complicado, como los de MVC, que mayormente tuve que reestructurar y ajustar yo mismo. La modificación de los tests de integración era muy sencilla, solo incluir que aceptara "Good morning", "Good afternoon" y "Good night", para no depender de la hora. 