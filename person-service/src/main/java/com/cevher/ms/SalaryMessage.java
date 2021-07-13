package com.cevher.ms;

import lombok.*;

import javax.persistence.Id;
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

    @Id
    @Getter
    @Setter
    private String uuid;
    private String fromService;
    private String toService;
    @Getter
    @Setter
    private String fromOperation;
    @Getter
    @Setter
    private String toOperation;
    @Getter
    @Setter
    private Long personId;
    @Getter
    @Setter
    private Double amount;
    @Getter
    @Setter
    private String salaryDate;
    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String message;



    public String getFromService() {
        return "person-service";
    }
    public String getToService() {
        return "salary-service";
    }

}