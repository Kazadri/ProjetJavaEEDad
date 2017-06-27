/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crypt.messagemgmt.model;


/**
 *
 * @author kazadri
 */
public class mot {
    private long id;
    private String nameWord;

    public mot(long id, String nameWord) {
        this.id = id;
        this.nameWord = nameWord;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameWord() {
        return nameWord;
    }

    public void setNameWord(String nameWord) {
        this.nameWord = nameWord;
    }
    
    
}
