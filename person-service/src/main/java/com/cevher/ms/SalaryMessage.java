package com.cevher.ms;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class SalaryMessage implements Serializable {

    private String toService;
    private String fromService;
    private String uuid;
    private String fromOperation;
    private String toOperation;
    private Long personId;
    private Double amount;
    private String salaryDate;

    public String getUuid() {
        return UUID.randomUUID().toString();
    }

    public String getFromService() {
        return "person-service";
    }
    public String getToService() {
        return "salary-service";
    }

}