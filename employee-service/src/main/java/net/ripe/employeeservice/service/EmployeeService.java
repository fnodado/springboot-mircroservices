package net.ripe.employeeservice.service;

import net.ripe.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);
}
