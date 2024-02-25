package com.example.WebTask.controller;

import com.example.WebTask.model.Task;
import com.example.WebTask.model.TaskStatus;
import com.example.WebTask.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Интеграционные тесты контролёра
 */
@SpringBootTest
public class TestController {
    @MockBean
    public TaskService taskService;
    @Mock
    public Model model;
    @Autowired
    public TaskWebController webController;
    public List<Task> taskList;
    public Task Task_1;
    public Task Task_2;

    /**
     * Инициализация параметров
     */
    @BeforeEach
    public void init() {
        taskList = new ArrayList<>();

        Task_1 = new Task();
        Task_1.setId(1L);
        Task_1.setDescription("Test 1");
        Task_1.setStatus(TaskStatus.NOT_STARTED);
        Task_1.setCreatedAt(LocalDateTime.now());

        Task_2 = new Task();
        Task_2.setId(2L);
        Task_2.setDescription("Test 2");
        Task_2.setStatus(TaskStatus.IN_PROGRESS);
        Task_2.setCreatedAt(LocalDateTime.now());
    }

    /**
     * Интеграционный тест запроса главной страницы сайта
     */
    @Test
    public void HomePageTest() {
//        given
        taskList.add(Task_1);
        taskList.add(Task_2);
        given(taskService.getAllTasks()).willReturn(taskList);
        given(model.addAttribute("tasks", taskList)).willReturn(model);
//        when
        webController.getAllTasks(model);
//        then
        verify(taskService).getAllTasks();
        verify(model).addAttribute("tasks", taskList);
    }
}
