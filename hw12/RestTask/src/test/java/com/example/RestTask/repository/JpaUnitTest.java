package com.example.RestTask.repository;

import com.example.RestTask.domain.Task;
import com.example.RestTask.domain.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaUnitTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Поиск по id
     */
    @Test
    public void findByIdTest() {
        // given
        Task task = new Task("Task Test", TaskStatus.NOT_STARTED);
        entityManager.persist(task);
        entityManager.flush();

        // when
        Task target = taskRepository.findById(task.getId()).get();

        // then
        assertEquals(target.getId(), task.getId());
        assertEquals(target.getDescription(), task.getDescription());
        assertEquals(target.getStatus(), task.getStatus());
        assertEquals(target.getCreatedAt(), task.getCreatedAt());
    }
}
