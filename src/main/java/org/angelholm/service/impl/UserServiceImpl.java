package org.angelholm.service.impl;

import org.angelholm.dao.UserDaoImpl;
import org.angelholm.model.User;
import org.angelholm.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String login) {

        UserDaoImpl userDao = new UserDaoImpl();

        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
