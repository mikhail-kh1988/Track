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

    @ExceptionHandler(NotFoundRoleUserException.class)
    protected ResponseEntity<ExceptionMessage> handeleNotFoundRoleUserException(){
        return new ResponseEntity<>(new ExceptionMessage("У пользователя не достаточно прав для выполнения данной операции!"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundSprintException.class)
    protected ResponseEntity<ExceptionMessage> handelNotFoundSprintException(){
        return new ResponseEntity<>(new ExceptionMessage("Не найден спринт!"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundIssueFromSprint.class)
    protected ResponseEntity<ExceptionMessage> handelNotFoundIssueFromSprint(){
        return new ResponseEntity<>(new ExceptionMessage("В указанном спринте ишью не обнаружено"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundUserException.class)
    protected ResponseEntity<ExceptionMessage> handelNotFoundUserException(){
        return new ResponseEntity<>(new ExceptionMessage("Пользователь не найден!"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundGroupException.class)
    protected ResponseEntity<ExceptionMessage> handleNotFoundGroupException(){
        return new ResponseEntity<>(new ExceptionMessage("Указанная группа не найдена"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class ExceptionMessage{
        private String error;
    }
}
