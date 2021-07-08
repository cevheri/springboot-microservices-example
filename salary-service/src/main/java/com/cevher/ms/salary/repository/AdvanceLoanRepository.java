package com.cevher.ms.salary.repository;

import com.cevher.ms.salary.domain. AdvanceLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface AdvanceLoanRepository
        extends JpaRepository<AdvanceLoan, Long> {

}
