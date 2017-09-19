package org.angelholm.model;

public class User {

    private String login;
    private String password;
    private boolean enables;

    public User() {

    }

    public User(String login, String password, boolean enables) {
        this.login = login;
        this.password = password;
        this.enables = enables;
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

    public boolean isEnables() {
        return enables;
    }

    public void setEnables(boolean enables) {
        this.enables = enables;
    }
}