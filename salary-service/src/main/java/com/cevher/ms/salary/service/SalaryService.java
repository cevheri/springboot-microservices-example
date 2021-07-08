package com.cevher.ms.salary.service;

import com.cevher.ms.salary.domain.PersonSalarySpec;
import com.cevher.ms.salary.domain.Salary;
import com.cevher.ms.salary.dto.SalaryDto;
import com.cevher.ms.salary.exception.AlreadySalaryException;
import com.cevher.ms.salary.repository.PersonSalarySpecRepository;
import com.cevher.ms.salary.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final PersonSalarySpecRepository personSalarySpecRepository;

    public List<SalaryDto> findAllByPersonId(Long personId) throws Exception {

        List<Salary> salaries = salaryRepository.findAllByPersonId(personId);
        if(salaries==null) throw new Exception("Person Salary Specification Not Found");

        return salaries.stream().map(s -> SalaryDto.builder()
                .id(s.getId())
                .personId(s.getPersonId())
                .salaryDate(s.getSalaryDate())
                .amount(s.getAmount())
                .build())
                .collect(Collectors.toList());
    }

    private void checkPersonSalarySpec(Long personId) {
        personSalarySpecRepository
                .findByPersonId(personId)
                .ifPresent(x -> {
                    throw new AlreadySalaryException("Current Salary Found");
                });
    }

//    private void checkSalaryWithTerm(Long personId, LocalDate salaryDate) {
//        salaryRepository
//                .findByPersonIdAndSalaryDate(personId, salaryDate)
//                .ifPresent(x -> {
//                    throw new AlreadySalaryException("Current Salary Found");
//                });
//    }

    public void savePersonSalarySpec(Long personId, Double amount) {
        PersonSalarySpec personSalary = PersonSalarySpec.builder()
                .personId(personId)
                .amount(amount)
                .build();
        personSalarySpecRepository.save(personSalary);
    }

    public void createPersonSalarySpec(Long personId, Double personSalary) {
        checkPersonSalarySpec(personId);
        savePersonSalarySpec(personId, personSalary);
    }

    public SalaryDto computeSalary(Long personId, LocalDate salaryDate) throws Exception {

        PersonSalarySpec personSalarySpec = personSalarySpecRepository
                .findByPersonId(personId)
                .orElseThrow(() -> new Exception("Person Salary Specification Not Found"));

        Salary salary = Salary.builder()
                .personId(personId)
                .createdAt(salaryDate)
                .createdBy("system")
                .amount(personSalarySpec.getAmount())
                .salaryDate(LocalDate.now())
                .build();

        salary = salaryRepository.save(salary);

        return SalaryDto.builder()
                .id(salary.getId())
                .personId(salary.getId())
                .amount(salary.getAmount())
                .salaryDate(salary.getSalaryDate())
                .build();

    }

    public SalaryDto getCurrentSalary(Long personId) throws Exception {
        Salary salary = salaryRepository
                .findByPersonId(personId)
                .orElseThrow(()-> new Exception("Person Salary Not Found"));
        return SalaryDto.builder()
                .id(salary.getId())
                .personId(salary.getPersonId())
                .salaryDate(salary.getSalaryDate())
                .amount(salary.getAmount())
                .build();
    }
}
