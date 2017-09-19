package org.angelholm.service.impl;

import org.angelholm.model.User;
import org.angelholm.service.UserService;
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
