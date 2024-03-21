package com.interview.project.employee.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "Employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "employeeId", example = "1", required = true)
    private Long employeeId;
    @Schema(name = "firstName", example = "Lionel", required = true)
    @Column(length = 20)
    private String firstName;
    @Schema(name = "lastName", example = "Messi", required = true)
    @Column(length = 20)
    private String lastName;
    @Schema(name = "emailAddress", example = "liomessi@imia.com", required = true)
    @Column(length = 20)
    private String emailAddress;
    @Schema(name = "phone", example = "333-1234567", required = false)
    @Column(length = 11)
    @Nullable
    private String phone;
    @Schema(name = "birthDate", example = "1990-09-19", required = true)
    private Date birthDate;
    @Schema(name = "job_title", example = "Operations Manager", required = true)
    @Column(length = 50)
    private String job_title;
    @Schema(name = "department", example = "IT", description = "Organization Department which employee belongs to",required = true)
    @Column(length = 20)
    private String department;
    @Schema(name = "location", example = "Miami, FL", description = "Geographical zone where employee lives in",required = true)
    @Column(length = 20)
    private String location;
    @Schema(name = "startDate", example = "2019-01-01", description = "Date in which the employee joined to the company", required = true)
    private Date startDate;
    @Schema(name = "reportingManager", example = "1", description = "Name of the person who the employee reports to", required = false)
    @Nullable
    private String reportingManager;
}
