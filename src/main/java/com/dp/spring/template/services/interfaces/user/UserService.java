package com.dp.spring.template.services.interfaces.user;

import com.dp.spring.template.models.user.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> findAll();

    User findById(int id);

    void validateUserByName(String username);

    void validateUserByEmail(String email);
}
