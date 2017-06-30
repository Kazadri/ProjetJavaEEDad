package com.crypt.messagemgmt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author imtek
 */
@Entity
@Table(name="statistics")
public class File implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="nameFile")
    private String nameFile;
    
    @Column(name="firstsWords")
    private String firstsWords;
        
    @Column(name="key")
    private String key;
    
    @Column(name="email")
    private String email;
    
    @Column(name="match")
    private Double match;

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
    public Double getMatch() {
        return match;
    }

    /**
     * @param match the match to set
     */
    public void setMatch(Double match) {
        this.match = match;
    }
    
 
    
}
