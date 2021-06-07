package com.cevher.ms.department.repository;


import com.cevher.ms.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    Department findByDepartmentId(Long departmentId);
}
