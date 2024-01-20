package com.example.hw3.repository;

import com.example.hw3.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с БД.
 */
@Component
public class UserRepository {

    private final JdbcTemplate jdbc; // Объект подключения к БД
    private List<User> users = new ArrayList<>();

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        users = getUsers();
    }

    /**
     * Получение списка пользователей.
     * @return список пользователей.
     */
    public List<User> getUsers() {
        String sql = "SELECT * FROM userTable";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowUser = new User();
            rowUser.setId(r.getLong("id"));
            rowUser.setName(r.getString("name"));
            rowUser.setAge(r.getInt("age"));
            rowUser.setEmail(r.getString("email"));
            return rowUser;
        };
        return jdbc.query(sql, userRowMapper);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Добавление пользователя в БД.
     * @param user объект пользователя.
     */
    public User addUser(User user) {
        String sql = "INSERT INTO userTable VALUES (DEFAULT, ?, ?, ?)";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail());
        return user;
    }

}