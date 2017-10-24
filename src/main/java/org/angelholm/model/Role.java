package org.angelholm.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

    private int id;
    private String name;
    private Set<User> users = new HashSet<User>(0);

    public Role() {
    }

    public Role(int id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers(){
        return this.users;
    }

    public void setUsers(Set<User> users){
        this.users = users;
    }
}
