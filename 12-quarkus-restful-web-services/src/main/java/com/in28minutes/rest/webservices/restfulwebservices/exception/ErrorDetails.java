package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp,
                           String message,
                           String details) {

}
