/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.test;

/**
 *
 * @author witta
 */
public class RandomNumberTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 10 + 1);
            System.out.println(random);
        }
    }
}
