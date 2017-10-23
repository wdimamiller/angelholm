package org.angelholm.dao;

import org.angelholm.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

     List<User> getAllUsers() throws SQLException;
     User getUserById(int userId) throws SQLException;
     User getUserByLogin(String userLogin) throws SQLException;

     void addUser(User user) throws SQLException;
     void updateUser(User user) throws SQLException;
     void deleteUser(User user) throws SQLException;

}
