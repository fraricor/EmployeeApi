package com.interview.project.employee.api;

import com.interview.project.employee.api.entity.Employee;
import com.interview.project.employee.api.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryTest
{
    @Autowired
    EmployeeRepository employeeRepository;

    Employee employee;

    @BeforeEach
    void setup()
    {
            employee = new Employee();
            employee.setFirstName("Francisco");
            employee.setLastName("Rio");
            employee.setEmailAddress("paco@mymail.com");
            employee.setPhone("333-1234567");
            employee.setBirthDate(Date.valueOf("1988-09-19"));
            employee.setJob_title("Manager");
            employee.setStartDate(Date.valueOf("2020-01-01"));
            employee.setLocation("Jersey City,NJ");
    }

    @Test
    public void saveEmployeeTest()
    {
        Employee persistedEmployee = employeeRepository.save(employee);
        Assertions.assertEquals("Francisco",persistedEmployee.getFirstName());
        Assertions.assertEquals("Rio",persistedEmployee.getLastName());
    }

    @Test
    public void getEmployeeByIdTest()
    {
        Employee employee = employeeRepository.findById(1l).get();
        Assertions.assertEquals("francisco",employee.getFirstName());
    }

    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> employees= employeeRepository.findAll();
        Assertions.assertEquals(2,employees.size());
    }

    @Test
    public void deleteEmployeeByIdTest()
    {
        Employee savedEmployee = employeeRepository.save(employee);
        Assertions.assertEquals(savedEmployee.getEmployeeId(),employeeRepository.findById(savedEmployee.getEmployeeId()).map(Employee::getEmployeeId).orElseThrow());
        employeeRepository.deleteById(savedEmployee.getEmployeeId());
        Assertions.assertEquals(Optional.empty(),employeeRepository.findById(savedEmployee.getEmployeeId()));
    }

    @Test
    public void updateUserTest()
    {
        Employee employeeToUpdate = employeeRepository.findById(1l).get();
        String previousName = employeeToUpdate.getFirstName();
        employeeToUpdate.setFirstName("updatedEmployee");
        employeeRepository.save(employeeToUpdate);
        Assertions.assertNotEquals(previousName,employeeRepository.findById(1l).map(Employee::getFirstName).orElseThrow());
        Assertions.assertEquals(employeeToUpdate.getFirstName(),employeeRepository.findById(1l).map(Employee::getFirstName).orElseThrow());
    }

}
