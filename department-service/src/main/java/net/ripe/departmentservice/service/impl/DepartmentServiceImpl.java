package net.ripe.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.ripe.departmentservice.dto.DepartmentDto;
import net.ripe.departmentservice.entity.Department;
import net.ripe.departmentservice.mapper.DepartmentMapper;
import net.ripe.departmentservice.repository.DepartmentRepository;
import net.ripe.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department savedDepartment = departmentRepository.save(DepartmentMapper.mapToJpa(departmentDto));

        return DepartmentMapper.mapToDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return DepartmentMapper.mapToDto(department);
    }


}
