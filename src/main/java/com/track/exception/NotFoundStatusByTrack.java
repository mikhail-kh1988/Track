package com.track.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundStatusByTrack extends RuntimeException{
    public NotFoundStatusByTrack(String message){
        super(message);
    }
}
