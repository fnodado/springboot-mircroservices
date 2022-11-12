package net.ripe.departmentservice.repository;

import net.ripe.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentCode(String code);

}
