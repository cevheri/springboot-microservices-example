package com.cevher.ms.salary.repository;

import com.cevher.ms.salary.domain.PersonSalarySpec;
import com.cevher.ms.salary.domain.Salary;
import com.cevher.ms.salary.dto.SalaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface PersonSalarySpecRepository
        extends JpaRepository<PersonSalarySpec, Long> {
   Optional<PersonSalarySpec> findByPersonId(Long personId);
}
