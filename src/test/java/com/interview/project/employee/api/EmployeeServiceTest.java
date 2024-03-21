package com.interview.project.employee.api;

import com.interview.project.employee.api.entity.Employee;
import com.interview.project.employee.api.exceptions.EmployeeAlreadyExistsException;
import com.interview.project.employee.api.repository.EmployeeRepository;
import com.interview.project.employee.api.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest
{
    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    private List<Employee> employees;
    private Employee employee;
    @Before
    public void setup()
    {
        employees = new ArrayList<>();
        employee = new Employee();
        employee.setEmployeeId(1l);
        employee.setFirstName("andres");
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        Mockito.when(employeeRepository.findById(1l)).thenReturn(Optional.ofNullable(employee));
        Mockito.when(employeeRepository.existsById(1l)).thenReturn(true);
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
    }

    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> retrievedList = employeeService.getAllEmployees();
        Assertions.assertEquals(employees,retrievedList);
    }

    @Test
    public void getEmployeeById()
    {
        Employee retrievedEmployee= employeeService.getEmployeeDetails(1l).get();
        Assertions.assertEquals(employee.getFirstName(),retrievedEmployee.getFirstName());
    }

    @Test
    public void createEmployeeAndValidatesDoesNotAlreadyExist() throws EmployeeAlreadyExistsException {
        employeeService.createEmployee(employee);
        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);
        Mockito.verify(employeeRepository,Mockito.times(1)).existsById(1l);
    }

    @Test
    public void deleteEmployee()
    {
        employeeService.deleteEmployee(1l);
        Mockito.verify(employeeRepository,Mockito.times(1)).deleteById(1l);
    }

    @Test
    public void updateEmployee()
    {
        employeeService.updateEmployee(1l,employee);
        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);
    }

    @Test(expected = EmployeeAlreadyExistsException.class)
    public void persistenceFailsWhenEmployeeAlreadyExists() throws EmployeeAlreadyExistsException {
        employeeService.createEmployee(employee);
        Mockito.verify(employeeRepository,Mockito.times(1)).existsById(1l);
    }
}
