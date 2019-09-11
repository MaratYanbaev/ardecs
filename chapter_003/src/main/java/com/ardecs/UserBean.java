package com.ardecs;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 12.04.2019
 */


public class UserBean {
    private String tuTu = "tuTu";

    public UserBean() {
    }

    public UserBean(String tuTu) {
        this.tuTu = tuTu;
    }

    public  void getTuTu() {
        System.out.println(tuTu);
    }
}
