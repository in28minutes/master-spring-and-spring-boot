package com.in28minutes.rest.webservices.restfulwebservices.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class UserNotFoundException(message: String) : RuntimeException(message)