package com.cevher.ms.department.web.rest;

import com.cevher.ms.department.domain.Department;
import com.cevher.ms.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("saveDepartment method of DepartmentController");
        return departmentService.saveDepartment(department);
    }
    @GetMapping("/")
    public List<Department> findAllDepartment() {
        log.info("Find All Department method DepartmentController");
        return departmentService.findAllDepartment();
    }
    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("findDepartmentById method of DepartmentController");
        return departmentService.findDepartmentById(departmentId);
    }

}
