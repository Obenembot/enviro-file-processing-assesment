package com.enviro.assessment.grad001.bridgettetambe.exception;

import jakarta.servlet.ServletException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice extends RuntimeException {


    @ExceptionHandler
    public ResponseEntity fileNotFoundException(FileNotFoundException fileNotFoundException) {
        return ResponseEntity.ok(fileNotFoundException);
    }


    @ExceptionHandler
    public ResponseEntity exception(Exception exception) {
        return ResponseEntity.ok(exception);
    }

    @ExceptionHandler
    public ResponseEntity exception(RuntimeException exception) {
        return ResponseEntity.ok(exception);
    }


    @ExceptionHandler
    public ResponseEntity servletExceptionException(ServletException exception) {
        return ResponseEntity.ok(exception.getMessage());
    }


}
