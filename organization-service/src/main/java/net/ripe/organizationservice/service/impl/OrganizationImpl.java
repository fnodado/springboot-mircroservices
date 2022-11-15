package net.ripe.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.organizationservice.dto.OrganizationDto;
import net.ripe.organizationservice.entity.Organization;
import net.ripe.organizationservice.mapper.OrganizationMapper;
import net.ripe.organizationservice.repository.OrganizationRepository;
import net.ripe.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class OrganizationImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;


    @Override
    public OrganizationDto saveEmployee(OrganizationDto organizationDto) {
        Organization savedEmployee = organizationRepository.save(OrganizationMapper.mapToJpa(organizationDto));


        return OrganizationMapper.mapToDto(savedEmployee);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        return OrganizationMapper.mapToDto(organizationRepository.findByOrganizationCode(organizationCode));
    }
}
