package com.cevher.ms.person.service;

import com.cevher.ms.person.domain.Person;
import com.cevher.ms.person.repository.PersonRepository;
import com.cevher.ms.person.vm.DepartmentVM;
import com.cevher.ms.person.vm.ResponseTempVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Person savePerson(Person person) {
        log.info("savePerson method of PersonService");
        return personRepository.save(person);
    }

    public ResponseTempVM getPersonWithDepartment(Long personId) {
        log.info("getPersonWithDepartment method of PersonService");

        ResponseTempVM vm = new ResponseTempVM();
        Person person = personRepository.findById(personId).orElse(new Person());

        // TODO : not for production, you should use config and common class or method.
        // TODO : We can use Message Broker for example Apache Kafka
        DepartmentVM department = restTemplate.getForObject(
                "http://department-service/departments/" + person.getDepartmentId()
                , DepartmentVM.class);

        vm.setPerson(person);
        vm.setDepartment(department);
        return vm;
    }

    public List<Person> findAllPerson() {
        log.info("findAllPerson method of PersonService");
        return personRepository.findAll();
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(new Person());
    }
}
