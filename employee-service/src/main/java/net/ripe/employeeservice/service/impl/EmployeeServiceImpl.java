package net.ripe.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.employeeservice.dto.APIResponseDto;
import net.ripe.employeeservice.dto.DepartmentDto;
import net.ripe.employeeservice.dto.EmployeeDto;
import net.ripe.employeeservice.entity.Employee;
import net.ripe.employeeservice.mapper.EmployeeMapper;
import net.ripe.employeeservice.repository.EmployeeRepository;
import net.ripe.employeeservice.service.APIClient;
import net.ripe.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(EmployeeMapper.mapToJpa(employeeDto));


        return EmployeeMapper.mapToDto(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeByIdUsingRestTemplate(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
                DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(EmployeeMapper.mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    @Override
    public APIResponseDto getEmployeeByIdUsingWebClient(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(EmployeeMapper.mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    @Override
    public APIResponseDto getEmployeeByIdUsingFeignClient(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());


        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(EmployeeMapper.mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }


}
