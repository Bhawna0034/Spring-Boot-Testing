package com.bhawna.SpringBoot.Testing.TestingApplication.services;

import com.bhawna.SpringBoot.Testing.TestingApplication.dtos.EmployeeDto;

public interface EmployeeService {

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}
