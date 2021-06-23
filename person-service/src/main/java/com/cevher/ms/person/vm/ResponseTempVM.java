package com.cevher.ms.person.vm;

import com.cevher.ms.person.domain.Person;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTempVM {
    private Person person;
    private DepartmentVM department;
}
