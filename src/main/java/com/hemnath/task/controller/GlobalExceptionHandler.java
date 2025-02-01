package com.hemnath.task.controller;

import com.hemnath.task.dto.ErrorResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponce> handleException(
            RuntimeException exception, WebRequest webRequest
    ){
        ErrorResponce errorResponce = new ErrorResponce(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
    }
}
