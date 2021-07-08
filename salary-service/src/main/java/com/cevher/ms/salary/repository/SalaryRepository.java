package com.cevher.ms.salary.repository;

import com.cevher.ms.salary.domain.Salary;
import com.cevher.ms.salary.dto.SalaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface SalaryRepository
        extends JpaRepository<Salary, Long> {
    Page<Salary> findAllByPersonId(Long personId, Pageable pageable);
    Optional<Salary> findByPersonIdAndSalaryDate(Long personId, LocalDate salaryDate);
}
