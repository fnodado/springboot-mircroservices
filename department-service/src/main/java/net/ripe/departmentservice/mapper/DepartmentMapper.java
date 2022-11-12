package net.ripe.departmentservice.mapper;

import net.ripe.departmentservice.dto.DepartmentDto;
import net.ripe.departmentservice.entity.Department;

public class DepartmentMapper {


    public static DepartmentDto mapToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setDepartmentCode(department.getDepartmentCode());
        dto.setDepartmentDescription(department.getDepartmentDescription());
        dto.setDepartmentName(department.getDepartmentName());

        return dto;
    }

    public static Department mapToJpa(DepartmentDto dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDepartmentDescription(dto.getDepartmentDescription());
        department.setDepartmentName(dto.getDepartmentName());

        return department;
    }

}
