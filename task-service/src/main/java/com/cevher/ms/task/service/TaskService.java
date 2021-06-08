package com.cevher.ms.task.service;

import com.cevher.ms.task.domain.Task;
import com.cevher.ms.task.repository.TaskRepository;
import com.cevher.ms.task.web.rest.UserVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTask() {
      log.info("findAllTask method of TaskService");
      return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        log.info("findAllTask method of TaskService");
        return taskRepository.save(task);
    }

    public Task findByTaskId(Long id) {
        log.info("findAllTask method of TaskService");
        return taskRepository.findById(id).orElse(new Task());
    }

    public Task findByTaskIdWithUserAndDepartment(Long id) {
        log.info("findAllTask method of TaskService");

        return taskRepository.findById(id).orElse(new Task());
    }
}
