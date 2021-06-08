package com.cevher.ms.department.repository;


import com.cevher.ms.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    Optional<Department> findById(Long departmentId);
}
