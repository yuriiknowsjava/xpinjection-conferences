package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import java.util.List;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ApiError;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.BusinessValidationException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityAlreadyExistsException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityDoesNotExist;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(BusinessValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiError> handleBusinessValidationException(BusinessValidationException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(new ApiError(errors), HttpStatus.BAD_REQUEST);
    }
}
