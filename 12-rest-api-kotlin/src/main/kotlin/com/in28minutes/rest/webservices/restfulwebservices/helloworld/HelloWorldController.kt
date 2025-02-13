/**
 * REST Controller for handling "Hello World" endpoints using Kotlin and Spring Boot.
 *
 * ## Features:
 * - **RESTful API**: Implements simple greeting endpoints using `@RestController`.
 * - **Internationalization (i18n)**: Uses `MessageSource` to return localized messages.
 * - **Path Variables**: Demonstrates handling dynamic values in URL paths.
 * - **Kotlin Concepts and Syntax Used**:
 *   - **Primary Constructor Injection**:
 *     ```kotlin
 *     class HelloWorldController(private val messageSource: MessageSource)
 *     ```
 *   - **String Interpolation**:
 *     ```kotlin
 *     return "Hello, $name"
 *     ```
 *   - **Nullable Type Handling**:
 *     ```kotlin
 *     fun helloWorldPathVariable(@PathVariable name: String?): HelloWorldBean
 *     ```
 *   - **Type Inference**:
 *     ```kotlin
 *     fun helloWorld() = "Hello World" // No explicit return type needed
 *     ```
 *   - **Data Class Usage**:
 *     ```kotlin
 *     data class HelloWorldBean(var message: String)
 *     ```
 *
 * ## Endpoints:
 * - `GET /hello-world` → Returns a simple "Hello World" string.
 * - `GET /hello-world-bean` → Returns a `HelloWorldBean` object.
 * - `GET /hello-world/path-variable/{name}` → Returns a personalized message.
 * - `GET /hello-world-internationalized` → Returns a localized greeting message.
 *
 * @constructor Initializes the controller with a `MessageSource` for internationalization.
 * @param messageSource The message source for retrieving localized messages.
 */

package com.in28minutes.rest.webservices.restfulwebservices.helloworld

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(private val messageSource: MessageSource) {

    @GetMapping(path = ["/hello-world"])
    fun helloWorld(): String {
        return "Hello World"
    }

    @GetMapping(path = ["/hello-world-bean"])
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello World")
    }

    // Path Parameters
    // /users/{id}/todos/{id}  => /users/2/todos/200
    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/Ranga
    @GetMapping(path = ["/hello-world/path-variable/{name}"])
    fun helloWorldPathVariable(@PathVariable name: String?): HelloWorldBean {
        return HelloWorldBean(String.format("Hello World, %s", name))
    }

    @GetMapping(path = ["/hello-world-internationalized"])
    fun helloWorldInternationalized(): String? {
        val locale = LocaleContextHolder.getLocale()
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale)

        //return "Hello World V2";

        //1:
        //2:
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)
    }
}