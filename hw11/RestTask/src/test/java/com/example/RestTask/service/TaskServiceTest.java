package com.example.RestTask.service;

import com.example.RestTask.domain.Task;
import com.example.RestTask.domain.TaskStatus;
import com.example.RestTask.exception.TaskNotFoundException;
import com.example.RestTask.repository.TaskRepository;
import com.example.RestTask.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

/**
 * Тестирование для сервис-слоя приложения
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    List<Task> tasks;

    @BeforeEach
    void setUp() {
        var task1 = Task.builder().id(1L).description("Task 1").status(TaskStatus.NOT_STARTED).build();
        var task2 = Task.builder().id(2L).description("Task 2").status(TaskStatus.IN_PROGRESS).build();

        tasks = List.of(task1, task2);
    }

    /**
     * Тест метода создания новой задачи
     * Успешно выполнение
     */
    @Test
    void createTaskTest_WillReturn_True() {
        var newTask = Task.builder().id(1L).description("Task 1").status(TaskStatus.NOT_STARTED).build();

        given(taskRepository.save(any(Task.class))).willReturn(newTask);

        boolean checkIsSaveTask = taskService.addTask(newTask);

        assertThat(newTask).isNotNull();
        assertTrue(checkIsSaveTask);
    }

    /**
     * Тест метода создания новой задачи
     * Неуспешное выполнение
     */
    @Test
    void createTaskTest_WillReturn_False() {
        var newTask = new Task();

        boolean checkIsSaveTask = taskService.addTask(newTask);

        assertFalse(checkIsSaveTask);
    }

    /**
     * Успешный тест метода удаления задачи
     */
    @Test
    void deleteTaskTest_isGood() {

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(tasks.get(0)));

        taskService.deleteTask(anyLong());

        verify(taskRepository).deleteById(anyLong());
    }

    /**
     * Удаление задачи
     * проверка на исключение TaskNotFoundException
     */
    @Test
    void deleteTaskTest_ThrowException() {
        given(taskRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.deleteTask(anyLong()));

        verify(taskRepository, never()).deleteById(anyLong());
    }

    /**
     * Список задач
     * Список задач
     */
    @Test
    void getAllTaskTest_ReturnCollectionTasks() {
        given(taskRepository.findAll()).willReturn(tasks);

        var tasksList = taskService.getAllTasks();

        assertEquals(tasks.size(), tasksList.size());
    }

    /**
     * Список задач
     * Пустой список
     */
    @Test
    void getAllTaskTest_ReturnEmptyCollection() {
        given(taskRepository.findAll()).willReturn(Collections.emptyList());

        var tasksList = taskService.getAllTasks();

        assertEquals(0, tasksList.size());
    }

    /**
     * Получение задачи по ID
     * Возврат объекта Task
     */
    @Test
    void getTaskByIdTest_WillReturnTask() {
        Task task = Task.builder()
                .id(1L).description("Task 1").build();

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        Task expectedTask = taskService.getTaskById(anyLong());

        assertEquals(expectedTask, task);
    }

    /**
     * Получение задачи по ID
     * Проверка на исключение
     */
    @Test
    void getTaskByIdTest_ThrowException() {

        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.getTaskById(anyLong()));
    }

    /**
     * Списпк задач по статусу
     * Список задач отфильтрованный по статусу
     */
    @Test
    void getTaskByStatusTest() {
        given(taskService.getTasksByStatus(TaskStatus.NOT_STARTED)).willReturn(
                List.of(Task.builder().status(TaskStatus.NOT_STARTED).build()));

        var tasksList = taskService.getTasksByStatus(TaskStatus.NOT_STARTED);

        assertEquals(1, tasksList.size());
    }

    /**
     * Изменение статуса задачи
     * true
     * Проверка текущего статуса с ожидаемым
     */
    @Test
    void updateTaskStatusTest_WillReturnUpdatedTask() {

        Task task = Task.builder().id(1L).description("Task 1").status(TaskStatus.NOT_STARTED).build();

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));

        boolean expected = taskService.updateTaskStatus(anyLong(), TaskStatus.COMPLETED);

        assertThat(task.getStatus()).isEqualTo(TaskStatus.COMPLETED);
        assertTrue(expected);
    }

    /**
     * Изменение статуса задачи
     * Проверка на исключение
     */
    @Test
    void updateTaskStatusTest_ThrowException() {

        given(taskRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.updateTaskStatus(anyLong(), TaskStatus.NOT_STARTED));
    }
}
