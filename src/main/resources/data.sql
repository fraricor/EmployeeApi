CREATE TABLE IF NOT EXISTS Employees(employeeId BIGINT auto_increment PRIMARY KEY,
                                    firstName VARCHAR(20) not null,
                                    lastName VARCHAR(20) not null,
                                    emailAddress VARCHAR(40) not null,
                                    phone VARCHAR(11),
                                    birthDate DATE not null,
                                    job_title VARCHAR(50) not null,
                                    department VARCHAR(20) not null,
                                    location VARCHAR(20) not null,
                                    startDate DATE,
                                    managerReporting BIGINT
                                    );

INSERT INTO Employees(firstName,lastName,emailAddress,phone,birthDate,job_title,department,location,startDate,reportingManager) values('francisco', 'rio', 'paco@mymail.com','333-1234567','1988-09-19','Java Developer', 'IT','Jersey City,NJ','2020-01-01','Luis');
INSERT INTO Employees(firstName,lastName,emailAddress,phone,birthDate,job_title,department,location,startDate,reportingManager) values('alejandra', 'cardiel', 'ale@mymail.com','333-1234567','1989-09-09','Product Owner','Operations','Dallas,TX','2018-09-01',null);