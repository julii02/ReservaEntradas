
package com.Entradas.Entradas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {
    @ExceptionHandler(EntradasException.class)
    public ResponseEntity<ExceptionDetails> handleDemoSpringCourseException(EntradasException ex) {
        ExceptionDetails details = ex.getDetails(); // Obtén el detalle de la excepción
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
