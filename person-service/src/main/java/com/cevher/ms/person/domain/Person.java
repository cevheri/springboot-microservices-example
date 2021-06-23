package com.cevher.ms.person.domain;


import lombok.*;
import io.swagger.annotations.ApiModel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PersonDomain", description = "Person Domain Model for person values")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long parentId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
}
