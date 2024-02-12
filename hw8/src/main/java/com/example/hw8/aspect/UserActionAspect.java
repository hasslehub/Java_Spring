package com.example.hw8.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class UserActionAspect {
    @Around("@annotation(com.example.hw8.aspect.TrackUserAction)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // Получение информации о вызываемом методе
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        // Получение информации о пользователе из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Логирование начала вызываемого метода
        log.info("Действие пользователя: {} - Метод {}.{} вызван с аргументами: {}", username, className,
                methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // Выполнение вызываемого метода
        long executionTime = System.currentTimeMillis() - start;

        // Логирование завершения вызываемого метода
        log.info("Действие пользователя: {} - Метод {} выполнился (Время выполнения: {}ms) с результатом - {} ",
                username, methodName, executionTime, result);

        return result;
    }
}
