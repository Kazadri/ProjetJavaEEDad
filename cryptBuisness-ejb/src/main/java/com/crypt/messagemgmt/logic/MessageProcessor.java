/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crypt.messagemgmt.logic;

import com.crypt.messagemgmt.model.MessageDecrypt;
import java.io.*;
import com.crypt.messagemgmt.model.mot;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author kazadri
 */
@MessageDriven(mappedName = "jms/messageQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/messageQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageProcessor implements MessageListener {
    
    public MessageProcessor() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            /**
             * 
             * Recupération du message
             * 
             **/
            String messageMessage = message.getBody(String.class);
            JAXBContext jaxbContext;
            MessageDecrypt m;
            try {
                jaxbContext = JAXBContext.newInstance(MessageDecrypt.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                StringReader reader = new StringReader(messageMessage);
                m = (MessageDecrypt) unmarshaller.unmarshal(reader); 
                System.out.println(m.getsMessage());

                /**
                 * 
                 * Recupération du dictionnaire
                 * 
                 **/

                Client client = ClientBuilder.newClient();
                WebTarget target = client.target("http://10.162.129.17:11080/dictionaryFacade-war/api/dictionary/words");
                Response resp = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get();
                List<mot> mots = new ArrayList<>();
                String jsonContent = resp.readEntity(String.class);

                try(JsonReader jreader = Json.createReader(new StringReader(jsonContent));){
                    //objet Java représentant un tableau json
                    JsonArray jArray = jreader.readArray();
                    for(int i = 0;i<jArray.size();i++){//pour chaque entrée du tableau
                    JsonObject jObject = jArray.getJsonObject(i);//on récupère l'objet json
                    //on récupère la valeur de chaque donnée
                    Long id =jObject.getJsonNumber("id").longValue();
                    String nameWord = jObject.getString("nameWord");
                    mots.add(new mot(id,nameWord));
                    }
                }

                /**
                 * 
                 * Traitement
                 * 
                 **/
                if(!mots.isEmpty()){
                    //On divise le message en liste de mots et on enlève la ponctuation
                    String[] dividedMessage = m.getsMessage().split("\\s+");
                    for (int i = 0; i < dividedMessage.length; i++) {
                        dividedMessage[i] = dividedMessage[i].replaceAll("[^\\w]", "");
                        System.out.println(dividedMessage[i]);
                    }
                    int count = 0;
                    for (int i = 0; i < dividedMessage.length; i++) {
                        for(mot mot : mots)
                        {
                            if(dividedMessage[i].equals(mot.getNameWord())){
                                count++;
                                break;
                            }
                        }
                    }
                    System.out.println(count);
                    /**
                     * Sortie :
                     * - Information secrete (adresser mail)
                     * - nom du fichier
                     * - truc déchiffrer
                     * - clé
                     */
                }
                          
            resp.close();
            client.close();
            } catch (JAXBException ex) {
                Logger.getLogger(MessageProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("l'ordre de decryptage "+messageMessage+" va être retiré de la queue");
        } catch (JMSException ex) {
            Logger.getLogger(MessageProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
