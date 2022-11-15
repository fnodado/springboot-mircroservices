package net.ripe.organizationservice.controller;

import lombok.AllArgsConstructor;
import net.ripe.organizationservice.dto.OrganizationDto;
import net.ripe.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;


    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        return new ResponseEntity<>(organizationService.saveEmployee(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("/{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("organization-code") String organizationCode){
        return new ResponseEntity<>(organizationService.getOrganizationByCode(organizationCode), HttpStatus.OK);
    }

}
