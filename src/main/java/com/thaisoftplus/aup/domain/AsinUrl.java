/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thaisoftplus.aup.domain;

import java.io.Serializable;

/**
 *
 * @author witta
 */
public class AsinUrl implements Serializable {

    private final int index;
    private final Object url;

    public AsinUrl(int index, Object url) {
        this.index = index;
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public Object getUrl() {
        return url;
    }
}
