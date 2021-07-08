package com.cevher.ms.salary.repository;

import com.cevher.ms.salary.domain.Salary;
import com.cevher.ms.salary.dto.SalaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface SalaryRepository
        extends JpaRepository<Salary, Long> {
    List<Salary> findAllByPersonId(Long personId);
    Optional<Salary> findByPersonIdAndSalaryDate(Long personId, LocalDate salaryDate);

    Optional<Salary> findByPersonId(Long personId);
}
