package com.cevher.ms.person.service;

import com.cevher.ms.person.domain.Person;
import com.cevher.ms.person.repository.PersonRepository;
import com.cevher.ms.SalaryMessage;
import com.cevher.ms.person.service.kafka.PersonSalaryProducer;
import com.cevher.ms.person.vm.DepartmentVM;
import com.cevher.ms.person.vm.ResponseTempVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;
    private final PersonSalaryProducer salaryProducer;

    private final Double PERSON_DEFAULT_SALARY = 1000D;

    private void sendFirstSalary(Person person) {
        log.info("sendFirstSalary method of PersonService for CurrentPerson");

        SalaryMessage salaryMessage = SalaryMessage
                .builder()
                .personId(person.getId())
                .salaryDate(LocalDate.now().toString())
                .amount(PERSON_DEFAULT_SALARY)
                .fromOperation("sendFirstSalary")
                .build();

        //salaryProducer.produce(salaryMessage.toString());
        salaryProducer.produce(salaryMessage);

        log.info("Send Kafka Message: " + salaryMessage);
    }

    public Person savePerson(Person person) {
        log.info("savePerson method of PersonService");
        Long personId = person.getId();
        personRepository.save(person);

        // if we created person send kafka message
        if (personId == null) {
            sendFirstSalary(person);
            log.info("Kafka savePerson message sent");
        }

        return person;
    }

    public ResponseTempVM getPersonWithDepartment(Long personId) {
        log.info("getPersonWithDepartment method of PersonService");

        val person = personRepository.findById(personId).orElse(new Person());

        // TODO : not for production, you should use config and common class or method.
        // TODO : We can use Message Broker for example Apache Kafka
        val url = "http://department-service/departments/" + person.getDepartmentId();
        val department = restTemplate.getForObject(url, DepartmentVM.class);

        return ResponseTempVM
                .builder()
                .person(person)
                .department(department)
                .build();
    }

    public List<Person> findAllPerson() {
        log.info("findAllPerson method of PersonService");
        return personRepository.findAll();
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(new Person());
    }
}
