package com.cevher.ms.user.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVM {
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
