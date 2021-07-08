package com.cevher.ms.salary.rest;

import com.cevher.ms.salary.dto.SalaryDto;
import com.cevher.ms.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class SalaryResource {
    private final SalaryService salaryService;


    public ResponseEntity<List<SalaryDto>> getAllSalaryByPersonId(Long personId, Pageable pageable) {
        var sal = salaryService.findAllByPersonId(personId, pageable);
        return ResponseEntity.ok().body(sal.getContent());
    }

    public void deleteSalary(Long personId, LocalDate salaryDate) {

    }

    @PostMapping("addSalary/{personId}/{salary}")
    public void addSalary(@PathVariable("personId") long personId,
                          @PathVariable("salary") double salary) {
        salaryService.addSalary(personId, salary);
    }

    public void addSalaryWithDate(Long person, Double salary, LocalDate salaryDate) {

    }

}
