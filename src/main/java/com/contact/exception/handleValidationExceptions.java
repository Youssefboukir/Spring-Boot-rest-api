package com.contact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class handleValidationExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ValidationFailedResponse> handleJwtExpiredException(MethodArgumentNotValidException ex, WebRequest request, HandlerMethod handlerMethod) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        ValidationFailedResponse response = ValidationFailedResponse.builder()
                .status(String.valueOf(status))
                .hints(errorMap)
                .path(
                        ((ServletWebRequest) request).getRequest().getServletPath()
                )
                .build();
        return new ResponseEntity<>(response, status);
    }
}
