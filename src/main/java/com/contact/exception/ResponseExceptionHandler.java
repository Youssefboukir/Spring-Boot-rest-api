package com.contact.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex , WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse exceptionResponse= ExceptionResponse.builder()
                .status(String.valueOf(status))
                .message(ex.getMessage())
                .path(
                        ((ServletWebRequest) request).getRequest().getServletPath()
                )
                .build();
        return new ResponseEntity(exceptionResponse, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> ResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse= ExceptionResponse.builder()
                .status(String.valueOf(status))
                .message(ex.getMessage())
                .path(
                        ((ServletWebRequest) request).getRequest().getServletPath()
                )
                .build();
        return new ResponseEntity(exceptionResponse, status);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<Object> AlreadyExistsException(AlreadyExistsException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse= ExceptionResponse.builder()
                .status(String.valueOf(status))
                .message(ex.getMessage())
                .path(
                        ((ServletWebRequest) request).getRequest().getServletPath()
                )
                .build();
        return new ResponseEntity(exceptionResponse, status);
    }
}
