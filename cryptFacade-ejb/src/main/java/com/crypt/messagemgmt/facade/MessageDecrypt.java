/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crypt.messagemgmt.facade;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kazadri
 */
@XmlRootElement
public class MessageDecrypt implements Serializable {
    private String sMessage;
    private String nFichier;
    private String key;
    /*
    **
    ** nom du fichier
    ** clé utilisé
    **
    */

    public String getsMessage() {
        return sMessage;
    }

    public void setsMessage(String sMessage) {
        this.sMessage = sMessage;
    }

    /**
     * @return the nFichier
     */
    public String getnFichier() {
        return nFichier;
    }

    /**
     * @param nFichier the nFichier to set
     */
    public void setnFichier(String nFichier) {
        this.nFichier = nFichier;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    
}
