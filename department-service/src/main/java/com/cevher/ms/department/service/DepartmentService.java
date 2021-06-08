package com.cevher.ms.department.service;

import com.cevher.ms.department.domain.Department;
import com.cevher.ms.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("saveDepartment method of DepartmentService");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("findDepartment method of DepartmentService");
        return departmentRepository.findById(departmentId).orElse(new Department());
    }

    public List<Department> findAllDepartment() {
        log.info("findAllDepartment method of DepartmentService");
        return departmentRepository.findAll();
    }
}
