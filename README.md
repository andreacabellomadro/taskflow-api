# TaskFlow REST API

API RESTful profesional construida con **Spring Boot 3** y **Java 17/21** para la gestión del ciclo de vida de tareas. Diseñada aplicando arquitectura limpia en capas, DTOs con inmutabilidad (Java Records), validaciones de entrada, control global de excepciones y documentación interactiva con Swagger OpenAPI.

---

## Stack Tecnológico

* **Lenguaje:** Java 17 / 21
* **Framework:** Spring Boot 3
* **Persistencia:** Spring Data JPA (Hibernate)
* **Base de datos:** H2 Database (motor relacional en memoria)
* **Validación de datos:** Bean Validation (`@Valid`, `@NotBlank`, `@Size`)
* **Productividad:** Project Lombok
* **Documentación interactiva:** SpringDoc OpenAPI 3 (Swagger UI)
* **Testing:** JUnit 5, MockMvc, Spring Boot Test

---

## Arquitectura y Buenas Prácticas

* **Separación de Responsabilidades:** Arquitectura clásica en capas desacopladas (`Controller` ➔ `Service` ➔ `Repository`).
* **Patrón DTO (Data Transfer Object):** Uso de Java Records para proteger la entidad interna de base de datos (`Task`), evitando fugas de información.
* **Manejo Centralizado de Excepciones:** Interceptador global con `@RestControllerAdvice` para capturar errores de validación (`400 Bad Request`) o recursos inexistentes (`404 Not Found`), devolviendo respuestas JSON estandarizadas.
* **Control de Transacciones:** Uso explícito de `@Transactional` y `@Transactional(readOnly = true)` para optimizar el acceso a datos.
* **Pruebas de Integración:** Verificación automática de rutas, códigos de estado HTTP y serialización con `MockMvc`.

---

## Cómo ejecutar el proyecto localmente

### 1. Clonar el repositorio
```bash
git clone [https://github.com/andreacabellomadro/taskflow-api.git](https://github.com/andreacabellomadro/taskflow-api.git)
cd taskflow-api