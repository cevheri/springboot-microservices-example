package com.cevher.ms.salary.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class SalaryDto {
    private Long id;
    private LocalDate salaryDate;
    private Integer salaryTerm;
    private Long personId;
    private Double amount;

    public Integer getSalaryTerm() {
        return salaryDate != null ? Integer.parseInt(salaryDate.format(DateTimeFormatter.ofPattern("yyyyMM"))) : null;
    }
}
