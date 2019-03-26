package com.ardecs.user;

import java.io.Serializable;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 13.03.2019
 */
public class User implements Serializable {
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}