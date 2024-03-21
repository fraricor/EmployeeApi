package com.interview.project.employee.api.service;

import com.interview.project.employee.api.entity.Employee;
import com.interview.project.employee.api.exceptions.EmployeeAlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface EmployeeService
{
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeDetails(long employeeId);
    void createEmployee(Employee newEmployee) throws EmployeeAlreadyExistsException;
    void deleteEmployee(long employeeId);
    void updateEmployee(long employeeId, Employee employee) throws NoSuchElementException;
}
