package com.moatazfarid.lab1_login_registration;

/**
 * Created by MoatazFarid on 3/3/2017.
 */

public class User {
    private int id; // user id
    private String name ; //User name
    private String password; // User password
    private String email; // User email

    public User(int id,String name, String email, String password) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
