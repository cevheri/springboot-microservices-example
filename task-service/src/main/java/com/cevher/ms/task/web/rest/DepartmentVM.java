package com.cevher.ms.task.web.rest;

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
