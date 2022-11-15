package net.ripe.employeeservice.mapper;

import net.ripe.employeeservice.dto.EmployeeDto;
import net.ripe.employeeservice.entity.Employee;

public class EmployeeMapper {


    public static EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setEmail(employee.getEmail());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setDepartmentCode(employee.getDepartmentCode());
        dto.setOrganizationCode(employee.getOrganizationCode());

        return dto;
    }

    public static Employee mapToJpa(EmployeeDto dto){
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setEmail(dto.getEmail());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDepartmentCode(dto.getDepartmentCode());
        employee.setOrganizationCode(dto.getOrganizationCode());

        return employee;
    }
}
