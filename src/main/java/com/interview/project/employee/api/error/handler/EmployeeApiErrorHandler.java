package com.interview.project.employee.api.error.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.interview.project.employee.api.exceptions.EmployeeAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class EmployeeApiErrorHandler
{
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> employeeNotFoundHandler(NoSuchElementException resourceNotFound)
    {
        return new ResponseEntity("Resource does not exist",HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpStatus> persitenceErrorHandler(SQLException persitenceError)
    {
        return new ResponseEntity(persitenceError.getCause(),HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity invalidFormatExceptionHandler(Exception appError)
    {
        return new ResponseEntity("Invalid request please review your request message structure",HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity employeeAlreadyExistsErrorHandler(EmployeeAlreadyExistsException appError)
    {
        return new ResponseEntity(appError.getMessage(),HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity generalExceptionHandler(Exception ex)
    {
        return new ResponseEntity("Something went wrong please try again later", HttpStatusCode.valueOf(500));
    }
}
