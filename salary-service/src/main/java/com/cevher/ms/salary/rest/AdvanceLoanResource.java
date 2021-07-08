package com.cevher.ms.salary.rest;

import com.cevher.ms.salary.dto.SalaryDto;
import com.cevher.ms.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class AdvanceLoanResource {
    private final AdvanceLoan advanceLoan;



}
