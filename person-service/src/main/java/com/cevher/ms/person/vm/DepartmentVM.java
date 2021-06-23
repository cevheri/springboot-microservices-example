package com.cevher.ms.person.vm;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVM {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String address;
}
