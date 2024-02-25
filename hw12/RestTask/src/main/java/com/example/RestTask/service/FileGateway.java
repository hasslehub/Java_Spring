package com.example.RestTask.service;

import com.example.RestTask.domain.Task;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Интерфейс интеграции
 */
@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface FileGateway {
    /**
     * Запись данных в канал интеграции
     * @param filename
     * @param task
     */
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, Task task);
}