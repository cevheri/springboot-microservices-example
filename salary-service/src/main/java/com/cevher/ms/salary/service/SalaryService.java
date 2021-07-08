package com.cevher.ms.salary.service;

import com.cevher.ms.salary.domain.Salary;
import com.cevher.ms.salary.dto.SalaryDto;
import com.cevher.ms.salary.exception.AlreadySalaryException;
import com.cevher.ms.salary.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryService {
    private final SalaryRepository salaryRepository;

    public Page<SalaryDto> findAllByPersonId(Long personId, Pageable pageable) {
        return salaryRepository.findAllByPersonId(personId, pageable)
                .map(s -> SalaryDto.builder()
                        .id(s.getId())
                        .personId(s.getPersonId())
                        .salaryDate(s.getSalaryDate())
                        .amount(s.getAmount())
                        .build());


    }

    private void checkSalaryByPerson(Long personId, LocalDate salaryDate) {
        salaryRepository
                .findByPersonIdAndSalaryDate(personId, salaryDate)
                .ifPresent(x -> {
                    throw new AlreadySalaryException("Current Salary Found");
                });
    }

    public void addSalary(Long personId, Double personSalary) {

        checkSalaryByPerson(personId, LocalDate.now());

        Salary salary = Salary.builder()
                .personId(personId)
                .createdAt(LocalDate.now())
                .createdBy("system")
                .amount(personSalary)
                .salaryDate(LocalDate.now())
                .build();
        salaryRepository.save(salary);
    }
}
