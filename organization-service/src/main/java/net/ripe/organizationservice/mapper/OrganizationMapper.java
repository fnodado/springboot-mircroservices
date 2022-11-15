package net.ripe.organizationservice.mapper;


import net.ripe.organizationservice.dto.OrganizationDto;
import net.ripe.organizationservice.entity.Organization;

public class OrganizationMapper {
    public static OrganizationDto mapToDto(Organization organization){
        OrganizationDto dto = new OrganizationDto();
        dto.setId(organization.getId());
        dto.setOrganizationDescription(organization.getOrganizationDescription());
        dto.setOrganizationCode(organization.getOrganizationCode());
        dto.setOrganizationName(organization.getOrganizationName());
        dto.setOrganizationCreatedDate(organization.getOrganizationCreatedDate());

        return dto;
    }

    public static Organization mapToJpa(OrganizationDto dto){
        Organization organization = new Organization();
        organization.setId(dto.getId());
        organization.setOrganizationDescription(dto.getOrganizationDescription());
        organization.setOrganizationCode(dto.getOrganizationCode());
        organization.setOrganizationName(dto.getOrganizationName());
        organization.setOrganizationCreatedDate(dto.getOrganizationCreatedDate());

        return organization;
    }

}
