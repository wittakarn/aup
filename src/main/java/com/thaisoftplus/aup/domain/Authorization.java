/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.domain;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Authorization implements Serializable {

    private String userName;
    private String password;

    /*
    rufussa6594@gmail.com   KooW@dg341    Alm0ndChocoL8   salpinx3280@gmail.com
    envenom2257@gmail.com   0wcYlpf2mbnY    sunday9630  salpinx3280@gmail.com
    */
    public Authorization(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
