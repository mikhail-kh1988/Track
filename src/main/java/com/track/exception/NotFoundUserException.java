package com.track.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(String message){
        super(message);
    }
}
