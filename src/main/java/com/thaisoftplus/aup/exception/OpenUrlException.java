/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.exception;

/**
 *
 * @author witta
 */
public class OpenUrlException extends Exception {

    private final int rowIndex;

    public OpenUrlException(Exception ex, int rowIndex) {
        super(ex);
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
