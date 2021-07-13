package com.cevher.ms.salary.service;


import com.cevher.ms.SalaryMessage;
import com.cevher.ms.salary.domain.AdvanceLoan;
import com.cevher.ms.salary.domain.PersonSalarySpec;
import com.cevher.ms.salary.domain.Salary;
import com.cevher.ms.salary.dto.AdvanceLoanDto;
import com.cevher.ms.salary.dto.PersonSalarySpecDto;
import com.cevher.ms.salary.dto.SalaryDto;
import com.cevher.ms.salary.exception.AlreadySalaryException;
import com.cevher.ms.salary.repository.AdvanceLoanRepository;
import com.cevher.ms.salary.repository.PersonSalarySpecRepository;
import com.cevher.ms.salary.repository.SalaryRepository;
import com.cevher.ms.salary.service.kafka.producer.PersonSalaryProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SalaryRepository salaryRepository;
    private final PersonSalarySpecRepository personSalarySpecRepository;
    private final AdvanceLoanRepository advanceLoanRepository;
    private final PersonSalaryProducer salaryProducer;

    public List<SalaryDto> findAllByPersonId(Long personId) throws Exception {

        List<Salary> salaries = salaryRepository.findAllByPersonId(personId);
        if (salaries == null) throw new Exception("Person Salary Specification Not Found");

        return salaries.stream().map(s -> SalaryDto.builder()
                .id(s.getId())
                .personId(s.getPersonId())
                .salaryDate(s.getSalaryDate())
                .amount(s.getAmount())
                .build())
                .collect(Collectors.toList());
    }

    private void checkPersonSalarySpec(Long personId) {
        personSalarySpecRepository
                .findByPersonId(personId)
                .ifPresent(x -> {
                    throw new AlreadySalaryException("Current Salary Found");
                });
    }

//    private void checkSalaryWithTerm(Long personId, LocalDate salaryDate) {
//        salaryRepository
//                .findByPersonIdAndSalaryDate(personId, salaryDate)
//                .ifPresent(x -> {
//                    throw new AlreadySalaryException("Current Salary Found");
//                });
//    }

    public void savePersonSalarySpec(PersonSalarySpecDto data) throws Exception {
        PersonSalarySpec personSalarySpec = PersonSalarySpec.builder()
                .id(data.getId())
                .createdAt(LocalDate.now())
                .createdBy("system")
                .personId(data.getPersonId())
                .amount(data.getAmount())
                .build();
        personSalarySpecRepository.save(personSalarySpec);
    }

    public void createPersonSalarySpec(Long personId, Double personSalary) throws Exception {
        checkPersonSalarySpec(personId);
        PersonSalarySpecDto dto = PersonSalarySpecDto
                .builder()
                .personId(personId)
                .amount(personSalary)
                .build();
        savePersonSalarySpec(dto);
    }

    public SalaryDto computeSalary(Long personId, String salaryDate) throws Exception {

        PersonSalarySpec personSalarySpec = personSalarySpecRepository
                .findByPersonId(personId)
                .orElseThrow(() -> new Exception("Person Salary Specification Not Found"));

        Salary salary = Salary.builder()
                .personId(personId)
                .createdAt(salaryDate)
                .createdBy("system")
                .amount(personSalarySpec.getAmount())
                .salaryDate(LocalDate.now())
                .build();

        salary = salaryRepository.save(salary);

        return SalaryDto.builder()
                .id(salary.getId())
                .personId(salary.getId())
                .amount(salary.getAmount())
                .salaryDate(salary.getSalaryDate())
                .build();

    }

    public SalaryDto getCurrentSalary(Long personId) throws Exception {
        Salary salary = salaryRepository
                .findByPersonId(personId)
                .orElseThrow(() -> new Exception("Person Salary Not Found"));
        return SalaryDto.builder()
                .id(salary.getId())
                .personId(salary.getPersonId())
                .salaryDate(salary.getSalaryDate())
                .amount(salary.getAmount())
                .build();
    }

    public AdvanceLoanDto demandAdvanceLoan(Long personId, Double demandedAmount) {
        //TODO : check multiple demand
        AdvanceLoan loan = AdvanceLoan.builder()
                .personId(personId)
                .amount(demandedAmount)
                .demandDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .createdBy("current-user")
                .build();
        loan = advanceLoanRepository.save(loan);
        return AdvanceLoanDto.builder()
                .personId(loan.getPersonId())
                .demandDate(loan.getDemandDate())
                .id(loan.getId())
                .amount(loan.getAmount())
                .build();
    }

    public void sendMessageToPerson(SalaryMessage salaryMessage) {
        String message = null;
        try {
            message = objectMapper.writeValueAsString(salaryMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        salaryProducer.produce(message);
    }

}
