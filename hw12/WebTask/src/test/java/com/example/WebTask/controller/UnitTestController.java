package com.example.WebTask.controller;

import com.example.WebTask.model.Task;
import com.example.WebTask.model.TaskStatus;
import com.example.WebTask.service.TaskService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UnitTestController {

    @Mock
    public TaskService siteService;
    @Mock
    public Model model;
    @InjectMocks
    public TaskWebController siteController;
    public List<Task> taskList;
    public Task test1;
    public Task test2;


    @BeforeEach
    public void init() {
        taskList = new ArrayList<>();

        test1 = new Task();
        test1.setId(1L);
        test1.setDescription("Title1");
        test1.setStatus(TaskStatus.NOT_STARTED);
        test1.setCreatedAt(LocalDateTime.now());

        test2 = new Task();
        test2.setId(2L);
        test2.setDescription("Title2");
        test2.setStatus(TaskStatus.IN_PROGRESS);
        test2.setCreatedAt(LocalDateTime.now());
    }

    /**
     * Запрос главной страницы
     */
    @Test
    public void getHomePageTest() {
//        given
        taskList.add(test1);
        taskList.add(test2);
        given(siteService.getAllTasks()).willReturn(taskList);
        given(model.addAttribute("notes", taskList)).willReturn(model);
//        when
        siteController.getAllTasks(model);
//        then
        verify(siteService).getAllTasks();
        verify(model).addAttribute("notes", taskList);
    }

    /**
     * Запрос страницы с данными заметки
     */
    @Test
    public void getNotePageTest() {
//        given
        given(siteService.getTaskById(1L)).willReturn(test1);
        given(model.addAttribute("description", test1.getDescription())).willReturn(model);
        given(model.addAttribute("NOT_STARTED", test1.getStatus())).willReturn(model);

//        when
        siteController.addTask("description");
//        then
        verify(siteService).getTaskById(1L);
        verify(model).addAttribute("text", test1.getDescription());
        verify(model).addAttribute("NOT_STARTED", test1.getStatus());
    }

    /**
     * Тест обновления параметров заметки
     * @param id
     * @param description
     * @param status
     */
    @ParameterizedTest
    @CsvSource(value = {"1, Test, NOT_STARTED"})
    public void changeTest(Long id, String description, TaskStatus status) {
//        given
        given(siteService.save(description)).willReturn(test1);
//        when
        siteController.updateTaskStatus(id, status);
//        then
        verify(siteService).save(description);
    }

}
