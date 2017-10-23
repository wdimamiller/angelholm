package org.angelholm.dao;

import org.angelholm.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles() throws SQLException;
    Role getRoleById(int roleId) throws SQLException;

    void addRole(Role role) throws SQLException;
    void updateRole(Role role) throws SQLException;
    void deleteRole(Role role) throws SQLException;

}
