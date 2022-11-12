package net.ripe.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.employeeservice.dto.EmployeeDto;
import net.ripe.employeeservice.entity.Employee;
import net.ripe.employeeservice.mapper.EmployeeMapper;
import net.ripe.employeeservice.repository.EmployeeRepository;
import net.ripe.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(EmployeeMapper.mapToJpa(employeeDto));


        return EmployeeMapper.mapToDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        return EmployeeMapper.mapToDto(employee.get());
    }
}
