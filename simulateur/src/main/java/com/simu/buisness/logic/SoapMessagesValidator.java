/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simu.buisness.logic;

import com.simu.integration.MessageService;
import com.simu.integration.MessagingEndpoint;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author kazadri
 */
@Stateless
public class SoapMessagesValidator implements MessageValidator {
    @WebServiceRef(MessageService.class)
    private MessagingEndpoint messaging;
    @Override
    public Boolean send(String message) {
        return messaging.messageOperation(message); 
    }

}
