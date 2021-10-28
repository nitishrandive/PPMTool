package com.ndroid.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request){
        ProjectIdExecptionResponse projectIdExceptionRespose = new ProjectIdExecptionResponse(ex.getMessage());
        return new ResponseEntity(projectIdExceptionRespose, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBacklogException(ProjectNotFoundException ex, WebRequest request){
        ProjectNotFoundExceptionResponse projectNotFoundExceptionResponse = new ProjectNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(projectNotFoundExceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidUserException(UserAlreadyExistsException ex, WebRequest request){
        UserAlreadyExistsExceptionResponse userAlreadyExistsExceptionResponse = new UserAlreadyExistsExceptionResponse(ex.getMessage());
        return new ResponseEntity(userAlreadyExistsExceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
