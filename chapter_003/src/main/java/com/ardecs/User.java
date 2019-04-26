package com.ardecs;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 12.04.2019
 */


public class User {
    private String tuTu = "tuTu";

    public User() {
    }

    public User(String tuTu) {
        this.tuTu = tuTu;
    }

    public void getTuTu() {
        System.out.println(tuTu);
    }
}
