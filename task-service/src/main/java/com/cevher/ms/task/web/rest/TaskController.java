package com.cevher.ms.task.web.rest;

import com.cevher.ms.task.domain.Task;
import com.cevher.ms.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public List<Task> findAllTask(){
        log.info("findAllTask method of TaskController");
        return taskService.findAllTask();
    }

    @PostMapping("/")
    public Task saveTask(@RequestBody Task task){
        log.info("saveTask method of TaskController");
        return taskService.saveTask(task);
    }

    @GetMapping("/{id}")
    public Task findByTaskId(@PathVariable("id") Long id){
        log.info("findByTaskId method of TaskController");
        return taskService.findByTaskId(id);
    }

    /**
     * Get Task with all relationship information
     * @param id task ID
     * @return Return Task with all relationship ResponseVM
     */
    @GetMapping("/rel/{id}")
    public ResponseVM findByTaskIdWithRelationShip(@PathVariable("id") Long id){
        log.info("findByTaskId method of TaskController");
        return taskService.findByTaskIdWithPersonAndDepartment(id);
    }
}
