package com.finnplay.test.service;

import com.finnplay.test.dao.UserDao;
import com.finnplay.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService
{
    private final UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, BCryptPasswordEncoder passwordEncoder)
    {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(Long id){
        return userDao.getOne(id);
    }

    @Override
    public void save(User user)
    {
//        User byEmail = findByEmail(user.getEmail());
         user.setPassword(passwordEncoder.encode(user.getPassword()));

//        if (byEmail != null)
//        {
//
//        }

        userDao.save(user);
    }

    @Override
    public User findByEmail(String email)
    {
//        User user = userDao.findByEmail(email);
//        boolean password = passwordEncoder.matches("password", user.getPassword());
//        user.setPassword(user.getPassword());
        return userDao.findByEmail(email);
    }

    public void update(User user)
    {
        user.setId(findByEmail(user.getEmail()).getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveAndFlush(user);
    }
}
