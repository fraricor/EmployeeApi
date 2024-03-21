package com.interview.project.employee.api.service.impl;

import com.interview.project.employee.api.entity.Employee;
import com.interview.project.employee.api.exceptions.EmployeeAlreadyExistsException;
import com.interview.project.employee.api.repository.EmployeeRepository;
import com.interview.project.employee.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeDetails(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public void createEmployee(Employee newEmployee) throws EmployeeAlreadyExistsException
    {
        if(newEmployee.getEmployeeId()!=null && employeeRepository.existsById(newEmployee.getEmployeeId()))
        {
            throw new EmployeeAlreadyExistsException();
        }
        employeeRepository.save(newEmployee);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void updateEmployee(long employeeId, Employee employee) throws NoSuchElementException
    {
        if(employeeRepository.findById(employeeId).isPresent())
        {
            employee.setEmployeeId(employeeId);
            employeeRepository.save(employee);
        }
        else
        {
         throw new NoSuchElementException();
        }
    }
}
