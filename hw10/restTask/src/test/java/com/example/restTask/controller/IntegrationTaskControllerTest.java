package com.example.restTask.controller;

import com.example.restTask.restTask.controller.TaskController;
import com.example.restTask.restTask.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Проерка TaskController, с БД
 */
@SpringBootTest
@ContextConfiguration(classes = TaskController.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class IntegrationTaskControllerTest {

    @MockBean
    TaskController taskController;
    @MockBean
    TaskRepository taskRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoad() {
        assertThat(taskController).isNotNull();
    }

    /**
     * Проверка метода получения списка задач
     */
    @Sql("tasks.sql")
    @Test
    void getAllTaskTest_isOkStatus() throws Exception {
        var requestBuilder = get("/rest");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    /**
     * проверка метода получения списка всех задач
     * когда в списоке нет записей
     * @throws Exception
     */
    @Sql("/trancate.sql")
    @Test
    void getAllTaskTest_isNoContent() throws Exception {
        var requestBuilder = get("/rest");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isNoContent()
                );
    }

    /**
     * Получение списка задач по статусу
     * OK
     * @throws Exception
     */
    @Sql("tasks.sql")
    @Test
    void getAllTaskByStatusTest_is2xxStatus() throws Exception {
        var requestBuilder = get("/status/IN_PROGRESS");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().is2xxSuccessful()
                );
    }

    /**
     * Получение списка задач по статусу
     * NO_CONTENT
     * @throws Exception
     */
    @Test
    void getAllTaskByStatusTest_isNoContent() throws Exception {
        var requestBuilder = get("/status/EMPTY");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isNoContent()
                );
    }
}
