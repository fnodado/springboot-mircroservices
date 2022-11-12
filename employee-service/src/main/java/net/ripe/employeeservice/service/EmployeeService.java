package net.ripe.employeeservice.service;

import net.ripe.employeeservice.dto.APIResponseDto;
import net.ripe.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeByIdUsingRestTemplate(Long employeeId);
    APIResponseDto getEmployeeByIdUsingWebClient(Long employeeId);

}
