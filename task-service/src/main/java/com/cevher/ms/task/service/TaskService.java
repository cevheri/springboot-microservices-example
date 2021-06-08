package com.cevher.ms.task.service;

import com.cevher.ms.task.domain.Task;
import com.cevher.ms.task.repository.TaskRepository;
import com.cevher.ms.task.web.rest.DepartmentVM;
import com.cevher.ms.task.web.rest.ResponseVM;
import com.cevher.ms.task.web.rest.UserVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    RestTemplate restTemplate;

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

    public ResponseVM findByTaskIdWithUserAndDepartment(Long id) {
        log.info("findByTaskIdWithUserAndDepartment method of TaskService");

        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            log.warn("findByTaskIdWithUserAndDepartment method of TaskService, task is empty");
            return null;
        }

        // TODO : not for production.
        // TODO : We can use Message Broker for example Apache Kafka
        UserVM user = restTemplate.getForObject(
                "http://user-service/users/" + task.get().getAssignedUserId()
                , UserVM.class);

        DepartmentVM department = restTemplate.getForObject(
                "http://department-service/departments/" + task.get().getDepartmentId()
                , DepartmentVM.class);
        ResponseVM responseVM = new ResponseVM(task.get(), user, department);
        return responseVM;
    }
}
