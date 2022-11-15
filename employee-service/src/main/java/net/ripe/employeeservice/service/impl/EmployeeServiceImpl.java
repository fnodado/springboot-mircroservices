package net.ripe.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.ripe.employeeservice.dto.APIResponseDto;
import net.ripe.employeeservice.dto.DepartmentDto;
import net.ripe.employeeservice.dto.EmployeeDto;
import net.ripe.employeeservice.dto.OrganizationDto;
import net.ripe.employeeservice.entity.Employee;
import net.ripe.employeeservice.mapper.EmployeeMapper;
import net.ripe.employeeservice.repository.EmployeeRepository;
import net.ripe.employeeservice.service.APIClient;
import net.ripe.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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

        ResponseEntity<OrganizationDto> organizationResponseEntity = restTemplate.getForEntity("http://localhost:8083/api/organizations/" + employee.getOrganizationCode(),
                OrganizationDto.class);
        OrganizationDto organization = organizationResponseEntity.getBody();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(EmployeeMapper.mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organization);

        return apiResponseDto;
    }

    //@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeByIdUsingWebClient(Long employeeId) {
        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(EmployeeMapper.mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

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

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and development department");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("DEF");
        organizationDto.setOrganizationCode("def001");
        organizationDto.setOrganizationDescription("DEF organization description");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(EmployeeMapper.mapToDto(employee));
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }


}
