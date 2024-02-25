package com.example.RestTask.controller;


import com.example.RestTask.controller.TaskController;
import com.example.RestTask.domain.Task;
import com.example.RestTask.domain.TaskStatus;
import com.example.RestTask.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

// Интекрационный тест
@SpringBootTest
@ContextConfiguration
public class TaskTest {
    @MockBean
    public TaskRepository taskRepository;

    @Autowired
    public TaskController taskController;

    /**
     * Сохранение
     */
    @Test
    public void saveTest() {

        Task task1 = new Task("Task 1", TaskStatus.NOT_STARTED);
        given(taskRepository.save(task1)).willReturn(task1);


        Task saveTask = (Task) taskController.addTask(String.valueOf(Optional.of(task1))).getBody();


        assertEquals(task1.getDescription(), Objects.requireNonNull(saveTask).getDescription());
        assertEquals(task1.getStatus(), Objects.requireNonNull(saveTask).getStatus());
    }
}
