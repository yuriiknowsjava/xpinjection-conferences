package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import java.util.List;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ApiErrorDto;
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
    ResponseEntity<ApiErrorDto> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return new ResponseEntity<>(new ApiErrorDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityDoesNotExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ApiErrorDto> handleEntityDoesNotExistException(EntityDoesNotExist e) {
        return new ResponseEntity<>(new ApiErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiErrorDto> handleBusinessValidationException(BusinessValidationException e) {
        return new ResponseEntity<>(new ApiErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(new ApiErrorDto(errors), HttpStatus.BAD_REQUEST);
    }
}
