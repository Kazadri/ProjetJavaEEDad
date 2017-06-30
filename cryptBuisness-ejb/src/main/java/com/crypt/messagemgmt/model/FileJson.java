package com.crypt.messagemgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author imtek
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FileJson implements Serializable {
    
    @XmlAttribute
    private Long id;
    
    @XmlElement
    private String nameFile;
    
    @XmlElement
    private String firstsWords;
        
    @XmlElement    
    private String key;
    
    @XmlElement    
    private String email;
    
    @XmlElement   
    private String match;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nameFile
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * @param nameFile the nameFile to set
     */
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    /**
     * @return the firstsWords
     */
    public String getFirstsWords() {
        return firstsWords;
    }

    /**
     * @param firstsWords the firstsWords to set
     */
    public void setFirstsWords(String firstsWords) {
        this.firstsWords = firstsWords;
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the match
     */
    public String getMatch() {
        return match;
    }

    /**
     * @param match the match to set
     */
    public void setMatch(String match) {
        this.match = match;
    }
    
}
