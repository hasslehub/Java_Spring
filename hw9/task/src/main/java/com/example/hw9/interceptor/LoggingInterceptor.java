package com.example.hw9.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

/**
 * Класс LoggingInterceptor реализует интерфейс HandlerInterceptor для логирования запросов.
 */
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Start Time: " + LocalDateTime.now());

        // Логирование запроса в файл
        logRequest(request);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        System.out.println("End Time: " + LocalDateTime.now());
        System.out.println("=======================================");
    }

    /**
     * Метод для логирования запроса в файл.
     *
     * @param request HttpServletRequest, представляющий текущий запрос.
     */
    private void logRequest(HttpServletRequest request) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("logs/logs.txt", true)))) {
            // Получение информации о пользователе из контекста безопасности
            String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "Anonymous";

            // Запись информации о запросе в файл
            writer.println("Time: " + LocalDateTime.now());
            writer.println("User: " + username);
            writer.println("URL: " + request.getRequestURL());
            writer.println("Method: " + request.getMethod());
            // Преобразование параметров запроса в читаемый вид
            writer.println("Parameters: " + getRequestParameters(request));
            writer.println("Request Body: " + getRequestBody(request));
            writer.println("///////////////////////////");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для получения параметров запроса в виде строки.
     *
     * @param request HttpServletRequest, представляющий текущий запрос.
     * @return Строка, содержащая параметры запроса в формате "param1=value1, param2=value2, ...".
     */
    private String getRequestParameters(HttpServletRequest request) {
        // Получаем список имен параметров запроса
        Enumeration<String> parameterNames = request.getParameterNames();

        // Преобразуем имена параметров и их значения в читаемый вид
        return Collections.list(parameterNames)
                .stream()
                .map(paramName -> paramName + "=" + Arrays.toString(request.getParameterValues(paramName)))
                .collect(Collectors.joining(", "));
    }

    /**
     * Метод для получения тела запроса.
     *
     * @param request HttpServletRequest, представляющий текущий запрос.
     * @return Тело запроса в виде строки.
     */
    private String getRequestBody(HttpServletRequest request) {
        try {
            return new BufferedReader(new InputStreamReader(request.getInputStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "Unable to read request body";
        }
    }
}
