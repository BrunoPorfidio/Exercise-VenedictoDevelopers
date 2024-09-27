package com.microservice.venedicto.security.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        List<ErrorResponse> errors = Arrays.asList(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
        return new ResponseEntity<>(new ErrorContainer(errors), HttpStatus.BAD_REQUEST);
    }

    private static class ErrorContainer {

        private List<ErrorResponse> error;

        public ErrorContainer(List<ErrorResponse> error) {
            this.error = error;
        }

        public List<ErrorResponse> getError() {
            return error;
        }
    }
}
