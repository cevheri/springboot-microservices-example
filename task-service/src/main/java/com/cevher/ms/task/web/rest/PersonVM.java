package com.cevher.ms.task.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVM {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;

}
