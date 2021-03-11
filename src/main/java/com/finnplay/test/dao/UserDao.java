package com.finnplay.test.dao;

import com.finnplay.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * https://habr.com/ru/post/263033/
 */

public interface UserDao extends JpaRepository<User,Long>
{
//    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
