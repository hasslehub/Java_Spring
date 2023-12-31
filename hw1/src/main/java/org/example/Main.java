package org.example;

/**
 Фреймворк Spring (семинары)
 Урок 1. Системы сборки Maven и Gradle для разработки Java приложений

 Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код, использующий эти зависимости.
 Задание:
 1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
 2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
 3. Создайте класс Person с полями firstName, lastName и age.
 4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
 5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.
 */

import com.google.gson.Gson;
public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(23); p.setFirstName("Василий"); p.setLastName("Теркин");
        System.out.println(p);
        System.out.println(new Person());

        Gson gson = new Gson();
        String json = gson.toJson(p);
        System.out.println("json = "+json);

        Person p2 = gson.fromJson(json, Person.class);
        System.out.println(p2);
        System.out.println(p2.equals(p));


    }

}