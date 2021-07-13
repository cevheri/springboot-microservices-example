package com.cevher.ms.salary.web.rest;

import com.cevher.ms.salary.dto.AdvanceLoanDto;
import com.cevher.ms.salary.dto.PersonSalarySpecDto;
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


    /**
     * When Person create, salary information will be recorded;
     * @param personId
     * @param salary
     * @throws Exception
     */
    @PostMapping("create-salary-spec/{personId}/{salary}")
    public void createSalary(@PathVariable("personId") Long personId,
                             @PathVariable("salary") Double salary) throws Exception {
        salaryService.createPersonSalarySpec(personId, salary);
    }

    @PutMapping("update-salary-spec")
    public void updateSalary(@RequestBody PersonSalarySpecDto personSalarySpecDto) throws Exception {
        salaryService.savePersonSalarySpec(personSalarySpecDto);
    }

    /**
     * In order to calculate salary by salary date for specific person
     * @param personId
     * @param salaryDate
     * @return SalaryDto
     * @throws Exception
     */
    @PostMapping("compute-salary/{personId}/{salaryDate}")
    public ResponseEntity<SalaryDto> computePersonSalary(@PathVariable("personId") Long personId,
                                                         @PathVariable
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                 String salaryDate)
            throws Exception {
        SalaryDto salaryDto = salaryService.computeSalary(personId, salaryDate);
        return ResponseEntity.ok().body(salaryDto);
    }

    /**
     * Return computed salary for specific person
     * @param personId
     * @return SalaryDto
     * @throws Exception
     */
    @GetMapping("get-current-salary/{personId}")
    public ResponseEntity<SalaryDto> getCurrentSalary(@PathVariable("personId") Long personId) throws Exception {
        SalaryDto salaryDto = salaryService.getCurrentSalary(personId);
        return ResponseEntity.ok(salaryDto);
    }

    /**
     * Return all computed salary
     * @param personId
     * @return
     * @throws Exception
     */
    @GetMapping("get-all-salary/{personId}")
    public ResponseEntity<List<SalaryDto>> getAllSalaryByPersonId(@PathVariable("personId") Long personId)
            throws Exception {
        var sal = salaryService.findAllByPersonId(personId);
        return ResponseEntity.ok().body(sal);
    }

    public ResponseEntity<AdvanceLoanDto> demandAdvanceLoan(Long personId, Double demandedAmound){
      AdvanceLoanDto dto = salaryService.demandAdvanceLoan(personId, demandedAmound);
      return ResponseEntity.ok(dto);
    }

}
