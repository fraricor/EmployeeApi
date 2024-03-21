package com.interview.project.employee.api.controller;

import com.interview.project.employee.api.entity.Employee;
import com.interview.project.employee.api.exceptions.EmployeeAlreadyExistsException;
import com.interview.project.employee.api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController()
@RequestMapping("/v1")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Retrieves the whole list of employees")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of employees successfully retrieved [event if list is empty]"),
            @ApiResponse(responseCode = "500", description = "Server could not process the request, please try again after some time")
    })
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        return ResponseEntity.ok()
                .body(employeeService.getAllEmployees());
    }

    @Operation(summary = "Retrieves an employee based on employeeId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee has been successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Employee does not exist"),
            @ApiResponse(responseCode = "500", description = "Server could not process the request, please try again after some time")
    })
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@Parameter(name = "id", description = "Id of employee we want to get details of") @PathVariable("id") long employeeId)
    {
        return ResponseEntity.ok()
                .body(employeeService.getEmployeeDetails(employeeId).orElseThrow(NoSuchElementException::new));
    }

    @Operation(summary = "Deletes an employee based on employeeId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee has been successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Employee does not exist"),
            @ApiResponse(responseCode = "500", description = "Server could not process the request, please try again after some time")
    })
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable("id") @Parameter(name = "id", description = "Id of employee we want to remove from DB")long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok()
                .body("Employee with id %s successfully deleted".formatted(employeeId));
    }

    @Operation(summary = "Creates a new employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee has been successfully created"),
            @ApiResponse(responseCode = "500", description = "Server could not process the request, please try again after some time")
    })
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee newEmployee) throws EmployeeAlreadyExistsException
    {
            employeeService.createEmployee(newEmployee);
            return ResponseEntity.ok()
                    .body("Employee has been successfully created");
    }

    @Operation(summary = "Updates an employee based on employeeId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee has been successfully updated"),
            @ApiResponse(responseCode = "404", description = "Employee does not exist"),
            @ApiResponse(responseCode = "500", description = "Server could not process the request, please try again after some time")
    })
    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") @Parameter(name = "id", description = "Id of employee we want to update to")long id,@RequestBody Employee updatedEmployee)
    {
        employeeService.updateEmployee(id,updatedEmployee);
        return ResponseEntity.ok()
                .body("Employee with id %s successfully deleted".formatted(id));
    }

}
