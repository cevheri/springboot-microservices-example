package com.cevher.ms.salary.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "salary", catalog = "public")
public class Salary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate salaryDate;
    private Long personId;
    private Double amount;
    private LocalDate createdAt;
    private String createdBy;
}
