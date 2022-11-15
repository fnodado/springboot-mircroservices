package net.ripe.organizationservice.service;

import net.ripe.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveEmployee(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String organizationCode);

}
