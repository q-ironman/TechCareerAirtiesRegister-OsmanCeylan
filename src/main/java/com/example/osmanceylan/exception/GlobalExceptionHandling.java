package com.example.osmanceylan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody ErrorResponse handlingUserNotFoundException(){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),ExceptionMessages.userNotFoundMessage);
    }
}
