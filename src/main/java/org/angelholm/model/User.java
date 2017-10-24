package org.angelholm.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private int id;
    private String login;
    private String password;
    private boolean enabled;
    private Set<Role> roles = new HashSet<>(0);

    public User() {

    }

    public User(int id, String login, String password, boolean enabled, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles(){
        return this.roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }
}