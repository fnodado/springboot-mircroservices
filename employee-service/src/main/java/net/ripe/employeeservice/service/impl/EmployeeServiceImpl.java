package net.ripe.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.employeeservice.dto.APIResponseDto;
import net.ripe.employeeservice.dto.DepartmentDto;
import net.ripe.employeeservice.dto.EmployeeDto;
import net.ripe.employeeservice.entity.Employee;
import net.ripe.employeeservice.mapper.EmployeeMapper;
import net.ripe.employeeservice.repository.EmployeeRepository;
import net.ripe.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(EmployeeMapper.mapToJpa(employeeDto));


        return EmployeeMapper.mapToDto(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
                DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(EmployeeMapper.mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
