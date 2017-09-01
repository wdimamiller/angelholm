package org.angelholm.security.service;

import org.angelholm.security.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String login) {
        User user = new User();
        user.setLogin(login);
        //хешуються sha1
        user.setPassword("d033e22ae348aeb5660fc2140aec35850c4da997");

        return user;
    }

}
