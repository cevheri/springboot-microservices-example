package com.cevher.ms.person.vm;

import com.cevher.ms.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTempVM {
    private Person person;
    private DepartmentVM department;
}
