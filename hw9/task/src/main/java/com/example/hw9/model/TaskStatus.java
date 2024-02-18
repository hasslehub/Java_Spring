package com.example.hw9.model;

/**
 * Перечисление TaskStatus представляет статус задачи в списке дел.
 * Возможные значения: NOT_STARTED (не начата), IN_PROGRESS (в процессе), COMPLETED (завершена).
 */
public enum TaskStatus {
    NOT_STARTED("не начата"),
    IN_PROGRESS("в процессе"),
    COMPLETED("завершена");

    private String displayValue;

    /**
     * Конструктор для инициализации экземпляра перечисления с указанием отображаемого значения.
     * @param displayValue Отображаемое значение статуса задачи.
     */
    TaskStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * Метод для получения отображаемого значения статуса задачи.
     * @return Отображаемое значение статуса задачи.
     */
    public String getDisplayValue() {
        return displayValue;
    }
}
