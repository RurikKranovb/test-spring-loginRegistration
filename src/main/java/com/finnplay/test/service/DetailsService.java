package com.finnplay.test.service;

import com.finnplay.test.dao.UserDao;
import com.finnplay.test.model.DetailsItem;
import com.finnplay.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class DetailsService implements UserDetailsService
{
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = userDao.findByEmail(s);

        return user == null ? NotFound() : new DetailsItem(user);
    }

    private UserDetails NotFound()
    {
        throw new UsernameNotFoundException("User not found");
    }
}
