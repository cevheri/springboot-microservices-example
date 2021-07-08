package com.cevher.ms.salary.web.rest;

import com.cevher.ms.salary.dto.SalaryDto;
import com.cevher.ms.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class SalaryResource {
    private final SalaryService salaryService;


    @PostMapping("create-salary-spec/{personId}/{salary}")
    public void createSalary(@PathVariable("personId") Long personId,
                             @PathVariable("salary") Double salary) {
        salaryService.createPersonSalarySpec(personId, salary);
    }

    @PutMapping("update-salary-spec/{personId}/{salary}")
    public void updateSalary(@PathVariable("personId") Long personId,
                             @PathVariable("salary") Double salary) {
        salaryService.savePersonSalarySpec(personId, salary);
    }


    @PostMapping("compute-salary/{personId}/{salaryDate}")
    public ResponseEntity<SalaryDto> computePersonSalary(@PathVariable("personId") Long personId,
                                                         @PathVariable
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                 LocalDate salaryDate)
            throws Exception {
        SalaryDto salaryDto = salaryService.computeSalary(personId, salaryDate);
        return ResponseEntity.ok().body(salaryDto);
    }

    @GetMapping("get-current-salary/{personId}")
    public ResponseEntity<SalaryDto> getCurrentSalary(@PathVariable("personId") Long personId) throws Exception {
        SalaryDto salaryDto = salaryService.getCurrentSalary(personId);
        return ResponseEntity.ok(salaryDto);
    }

    @GetMapping("get-all-salary/{personId}")
    public ResponseEntity<List<SalaryDto>> getAllSalaryByPersonId(@PathVariable("personId") Long personId)
            throws Exception {
        var sal = salaryService.findAllByPersonId(personId);
        return ResponseEntity.ok().body(sal);
    }

}
