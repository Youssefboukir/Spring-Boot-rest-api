package com.contact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExistsException extends IllegalStateException{
    public AlreadyExistsException(String message){
        super(message);
    }
}
