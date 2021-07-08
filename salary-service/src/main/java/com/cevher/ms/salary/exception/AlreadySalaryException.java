package com.cevher.ms.salary.exception;

public class AlreadySalaryException extends RuntimeException {
    public AlreadySalaryException(String current_salary_found) {
        super(current_salary_found);
    }
}
