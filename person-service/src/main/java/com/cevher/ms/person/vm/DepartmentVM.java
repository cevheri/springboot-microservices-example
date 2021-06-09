package com.cevher.ms.person.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVM {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String address;
}
