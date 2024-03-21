package com.interview.project.employee.api.exceptions;

public class EmployeeAlreadyExistsException extends Exception
{
    public EmployeeAlreadyExistsException()
    {
        super("User with provided id already exists");
    }
}
