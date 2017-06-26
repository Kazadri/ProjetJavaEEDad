/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simu.buisness.logic;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kazadri
 */
@Stateless
@Rest
public class RestMessagesValidator implements MessageValidator {

    @Override
    public Boolean send(String message) {
        JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
        JsonObject messageObject = messageBuilder.add("message",message).build();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:11080/cryptFacade-war/webresources/message");
        Response resp = target.request()
            .post(Entity.entity(messageObject.toString(), MediaType.APPLICATION_JSON_TYPE));
        boolean success;
        if(resp.getStatus()==202){
            success = true;
        }else{
            success = false;
        }
        resp.close();
        client.close();
        return success;
    }

}
