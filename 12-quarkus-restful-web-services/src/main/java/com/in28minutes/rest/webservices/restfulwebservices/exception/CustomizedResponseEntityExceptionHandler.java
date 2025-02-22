package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.validation.ConstraintViolationException;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

/**
 * 1. Use <code>@Provider</code> instead of <code>@ControllerAdvice</code> <br/>
 * 2. Implement <code>ExceptionMapper<T></code> for each exception type <br/>
 * 3. Use <code>Response</code> instead of <code>ResponseEntity</code> <br/>
 * <a href="https://quarkus.io/guides/validation#rest-end-point-validation">ExceptionMapper</a>
 */
@Provider
public class CustomizedResponseEntityExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        return switch (ex) {
            case UserNotFoundException userNotFoundException -> handleUserNotFoundException(userNotFoundException);
            case ConstraintViolationException validationException -> handleValidationException(validationException);
            default -> handleGenericException(ex);
        };
    }

    private Response handleUserNotFoundException(UserNotFoundException ex) {
        var errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), "User not found");

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorDetails)
                .build();
    }

    private Response handleValidationException(ConstraintViolationException ex) {
        String firstErrorMessage = ex.getConstraintViolations().iterator().next().getMessage();
        var errorDetails = new ErrorDetails(LocalDateTime.now(),
                "Total Errors: " + ex.getConstraintViolations().size() +
                        " First Error: " + firstErrorMessage, "Validation error");

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorDetails)
                .build();
    }

    private Response handleGenericException(Exception ex) {
        var errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), "An unexpected error occurred");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorDetails)
                .build();
    }
}
