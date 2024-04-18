package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ApiError;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityAlreadyExistsException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityDoesNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CommonExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<ApiError> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityDoesNotExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ApiError> handleEntityDoesNotExistException(EntityAlreadyExistsException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
