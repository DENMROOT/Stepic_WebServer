package com.denm.server.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by DENM on 20.01.2016.
 */
@Entity
@Table(name = "USER")
public class User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "password", unique = true, updatable = false)
    private String password;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public User() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public User(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public User(String name, String password) {
        this.setId(-1);
        this.setName(name);
        this.setPassword(password);
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}