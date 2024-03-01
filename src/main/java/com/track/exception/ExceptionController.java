package com.track.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionMessage> handleException(){
        return new ResponseEntity<>(new ExceptionMessage("Системная ошибка"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundInternalUserException.class)
    protected ResponseEntity<ExceptionMessage> handleNotFoundInternalUserException(){
        return new ResponseEntity<>(new ExceptionMessage("По указанному ID пользователь не найден."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundIssue.class)
    protected ResponseEntity<ExceptionMessage> handelNotFoundIssue(){
        return new ResponseEntity<>(new ExceptionMessage("По указанному вненему ID, сущность не обнаружена!"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class ExceptionMessage{
        private String error;
    }
}
