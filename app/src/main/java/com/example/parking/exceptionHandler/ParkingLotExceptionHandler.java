package com.example.parking.exceptionHandler;

import com.example.exception.BadRequestException;
import com.example.exception.UnprocessableEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ParkingLotExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(BadRequestException ex) {
        return ErrorResponse
                .builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage())
                .type(URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400"))
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        return ErrorResponse
                .builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .type(URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404"))
                .build();
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ErrorResponse handleUnprocessableEntityException(UnprocessableEntityException ex) {
        return ErrorResponse
                .builder(ex, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage())
                .type(URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/422"))
                .build();
    }

    @ExceptionHandler(IdentifierGenerationException.class)
    public ErrorResponse handleIdentifierGenerationException(IdentifierGenerationException ex) {
        return ErrorResponse
                .builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage())
                .type(URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400"))
                .build();
    }
}
