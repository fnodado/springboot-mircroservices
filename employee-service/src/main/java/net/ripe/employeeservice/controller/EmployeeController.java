package net.ripe.employeeservice.controller;


import lombok.AllArgsConstructor;
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

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

}
