/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crypt.messagemgmt.facade;

import java.io.StringWriter;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
public class MessagingServiceBean implements MessageServiceEndpointInterface, MessageServiceRemote {
   
    @Inject //paquetage javax.inject
    private JMSContext context; //paquetage javax.jms

    @Resource(lookup = "jms/messageQueue") //paquetage javax.annotation
    private Queue messageQueue; //paquetage javax.jms
 
    @Override
    public Boolean verification(String message, String key, String nameFile){
        MessageDecrypt m = new MessageDecrypt();
       if(message.length() > 0){
           m.setsMessage(message);
           m.setKey(key);
           m.setnFichier(nameFile);
           
           sendMessage(m);
           return true;
       }else{
           return false;
       }
   }
    
    private void sendMessage(MessageDecrypt message){
        JAXBContext jaxbContext;
        try {
        //obtention d'une instance JAXBContext associée au type Payment annoté avec JAX-B
            jaxbContext = JAXBContext.newInstance(MessageDecrypt.class);
            //création d'un Marshaller pour transfomer l'objet Java en flux XML
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            StringWriter writer = new StringWriter();

            //transformation de l'objet en flux XML stocké dans un Writer
            jaxbMarshaller.marshal(message, writer);
            String xmlMessage = writer.toString();
            //affichage du XML dans la console de sortie
            System.out.println(xmlMessage);
            TextMessage msg = context.createTextMessage(xmlMessage);
            context.createProducer().send(messageQueue, msg);
        } catch (JAXBException ex) {
            Logger.getLogger(MessagingServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
