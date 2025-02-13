/**
 * Global Exception Handler for customizing API error responses.
 *
 * ## Features:
 * - Uses `@ControllerAdvice` to handle exceptions **globally** across the application.
 * - **Extends** `ResponseEntityExceptionHandler` for handling validation errors.
 * - Returns structured error details using the `ErrorDetails` data class.
 * - Improves **API consistency** by standardizing error responses.
 *
 * ## Handled Exceptions:
 * - **Generic Exception (`Exception`)** → HTTP 500 (Internal Server Error)
 * - **UserNotFoundException** → HTTP 404 (Not Found)
 * - **Validation Errors (`MethodArgumentNotValidException`)** → HTTP 400 (Bad Request)
 *
 * ## Example Error Response:
 * ```json
 * {
 *   "timestamp": "2025-02-13T14:13:27",
 *   "message": "User not found",
 *   "details": "URI=/users/100"
 * }
 * ```
 *
 * ## Usage in a Controller:
 * ```kotlin
 * @GetMapping("/users/{id}")
 * fun getUser(@PathVariable id: Int): User {
 *     return userService.findById(id) ?: throw UserNotFoundException("User with id $id not found")
 * }
 * ```
 *
 * @see ResponseEntityExceptionHandler
 */
package com.in28minutes.rest.webservices.restfulwebservices.exception

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * Handles **all uncaught exceptions** and returns a generic error response.
     *
     * @param ex The thrown exception.
     * @param request The web request triggering the exception.
     * @return `ResponseEntity<ErrorDetails>` with HTTP 500 status.
     */
    @ExceptionHandler(Exception::class)
    @Throws(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            LocalDateTime.now(),
            ex.message!!, request.getDescription(false)
        )

        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * Handles `UserNotFoundException` and returns a **404 Not Found** response.
     *
     * @param ex The thrown exception.
     * @param request The web request triggering the exception.
     * @return `ResponseEntity<ErrorDetails>` with HTTP 404 status.
     */
    @ExceptionHandler(UserNotFoundException::class)
    @Throws(Exception::class)
    fun handleUserNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            LocalDateTime.now(),
            ex.message!!, request.getDescription(false)
        )

        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    /**
     * Handles **validation errors** (e.g., missing/invalid request fields).
     *
     * @param ex The thrown `MethodArgumentNotValidException`.
     * @param headers HTTP headers.
     * @param status The HTTP status code.
     * @param request The web request triggering the validation failure.
     * @return `ResponseEntity<ErrorDetails>` with HTTP 400 status.
     */
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any>? {
        val errorDetails = ErrorDetails(
            LocalDateTime.now(),
            "Total Errors:" + ex.errorCount + " First Error:" + ex.fieldError!!.defaultMessage,
            request.getDescription(false)
        )

        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}