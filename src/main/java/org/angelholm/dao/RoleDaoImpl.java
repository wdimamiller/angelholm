package org.angelholm.dao;


import org.angelholm.model.Role;
import org.angelholm.util.HibernateUtil;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    public void addRole(Role role) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            //TODO normal exception
            System.out.println("Помилка при вставці ролі" + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateRole(Role role) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            //TODO normal exception
            System.out.println("Помилка при вставці" + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Role getRoleById(int roleId) throws SQLException {
        Session session = null;
        Role role = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            role = (Role) session.load(Role.class, roleId);
        } catch (Exception e) {
            //TODO normal exception
            System.out.println("Помилка пошуку по id " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return role;
    }


    public List<Role> getAllRoles() throws SQLException {
        Session session = null;
        List users = new ArrayList<Role>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //TODO something with this fucking depricated method
            users = session.createCriteria(Role.class).list();
        } catch (Exception e) {
            //TODO normal exception
            System.out.println("Помилка пошуку всіх ролей " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    public void deleteRole(Role role) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            //TODO normal exception
            System.out.println("Помилка при видаленні " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }



}