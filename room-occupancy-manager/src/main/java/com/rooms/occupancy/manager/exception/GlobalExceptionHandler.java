package com.rooms.occupancy.manager.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PotentialGuestsException.class})
    public ResponseEntity<Object> potentialGuestsException(final Exception exception, final WebRequest request) {
        final ErrorResponse errors = new ErrorResponse();
        errors.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST));
        errors.setDescription("Potential Guests Error: "+exception.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST);
        errors.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FileNotFoundException.class})
    public ResponseEntity<Object> fileNotFound(final Exception exception, final WebRequest request) {
        final ErrorResponse errors = new ErrorResponse();
        errors.setErrorCode(String.valueOf(HttpStatus.NOT_FOUND));
        errors.setDescription("Potential Guests file not found " + exception.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND);
        errors.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
