package com.cevher.ms.person.service;

import com.cevher.ms.person.domain.Person;
import com.cevher.ms.person.repository.PersonRepository;
import com.cevher.ms.SalaryMessage;
import com.cevher.ms.person.service.kafka.PersonSalaryProducer;
import com.cevher.ms.person.vm.DepartmentVM;
import com.cevher.ms.person.vm.ResponseTempVM;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;
    private final PersonSalaryProducer salaryProducer;

    private final Double PERSON_DEFAULT_SALARY = 1000D;

    private void sendFirstSalary(Person person) {
        log.info("sendFirstSalary method of PersonService for CurrentPerson");

        SalaryMessage salaryMessage = SalaryMessage
                .builder()
                .uuid(UUID.randomUUID().toString())
                .fromOperation("sendFirstSalary")
                .personId(person.getId())
                .salaryDate(LocalDate.now().toString())
                .amount(PERSON_DEFAULT_SALARY)
                .code("0000")
                .message("SUCCESS")
                .build();

        //salaryProducer.produce(salaryMessage.toString());
        String message = null;
        try {
            message = objectMapper.writeValueAsString(salaryMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        salaryProducer.produce(message);

        log.info("Sended sendFirstSalary Message: " + salaryMessage.getUuid());
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
