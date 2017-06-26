/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simu.model;

import com.simu.buisness.logic.MessageValidator;
import com.simu.buisness.logic.Rest;
import com.simu.buisness.logic.Soap;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kazadri
 */
@Named(value = "messageModel")
@RequestScoped
public class MessageBean {

    private String message;
    @Inject @Soap
    private MessageValidator messageValidator;
    @Inject @Rest
    private MessageValidator restMessageValidator;
    
    public String doMessageWithSoap(){
        boolean isValid = messageValidator.send(message);
        if(isValid){
            return "valid";
        }else{
            return "invalid";
        }
    }
    public String doMessageWithRest(){
        boolean isValid = restMessageValidator.send(message);
        if(isValid){
            return "valid";
        }else{
            return "invalid";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
