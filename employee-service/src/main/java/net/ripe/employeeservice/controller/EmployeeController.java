package net.ripe.employeeservice.controller;


import lombok.AllArgsConstructor;
import net.ripe.employeeservice.dto.APIResponseDto;
import net.ripe.employeeservice.dto.EmployeeDto;
import net.ripe.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/rest-template/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeByIdUsingRestTemplate(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.getEmployeeByIdUsingRestTemplate(id), HttpStatus.OK);
    }

    @GetMapping("/web-client/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeByIdUsingWebClient(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.getEmployeeByIdUsingWebClient(id), HttpStatus.OK);
    }

    @GetMapping("/feign-client/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeByIdUsingFeignClient(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.getEmployeeByIdUsingFeignClient(id), HttpStatus.OK);
    }

}
