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

    @ExceptionHandler(Exception::class)
    @Throws(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            LocalDateTime.now(),
            ex.message!!, request.getDescription(false)
        )

        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UserNotFoundException::class)
    @Throws(Exception::class)
    fun handleUserNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(
            LocalDateTime.now(),
            ex.message!!, request.getDescription(false)
        )

        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

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