/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crypt.messagemgmt.facade;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author kazadri
 */
@Stateless
@WebService(
endpointInterface = "com.crypt.messagemgmt.facade.MessageServiceEndpointInterface",
portName = "MessagePort",
serviceName = "MessageService"
)
public class MessagingServiceBean implements MessageServiceEndpointInterface {
   @Override
   public Boolean verification(String message){
       if(message.length() > 0)
           return true;
       else{
           return false;
       }
   }
}
