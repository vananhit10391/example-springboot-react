package vananh.example.multitenant.config;

import vananh.example.common.exception.BadRequestException;
import vananh.example.common.exception.ErrorMessage;
import vananh.example.common.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * All exceptions haven't define will be handle to be here
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
        return new ErrorMessage(10000, ex.getLocalizedMessage());
    }

    /**
     * ResourceNotFoundException will be handle to be here
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(Exception ex, WebRequest request) {
        return new ErrorMessage(10001, ex.getLocalizedMessage());
    }

    /**
     * AuthenticationException will be handle to be here
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleAuthenticationException(Exception ex, WebRequest request) {
        return new ErrorMessage(10002, ex.getLocalizedMessage());
    }

    /**
     * BadRequestException will be handle to be here
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(Exception ex, WebRequest request) {
        return new ErrorMessage(10003, ex.getLocalizedMessage());
    }
}
