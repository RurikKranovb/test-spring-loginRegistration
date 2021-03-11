package com.finnplay.test.service;

import com.finnplay.test.model.User;

public interface IUserService
{
    void save(User user);
    void update(User user);

    User findByEmail(String email);
}
