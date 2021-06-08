package com.cevher.ms.task.web.rest;


import com.cevher.ms.task.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVM {
    private Task task;
    private UserVM user;
    private DepartmentVM department;
}

