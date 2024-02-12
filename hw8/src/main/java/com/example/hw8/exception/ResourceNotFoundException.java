package com.example.hw8.exception;

/**
 * Класс ResourceNotFoundException представляет исключение, выбрасываемое при отсутствии ресурса
 * (например, задачи) в списке дел.
 * Расширяет базовый класс RuntimeException.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Конструктор для создания экземпляра исключения с пользовательским сообщением.
     * @param message Сообщение об ошибке, описывающее причину исключения.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
