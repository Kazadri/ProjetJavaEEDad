/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crypt.messagemgmt.facade;

import java.io.StringReader;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author kazadri
 */
@Path("message")
@RequestScoped
public class MessageResource {

    @EJB(lookup = "java:global/cryptFacade-ejb/MessagingServiceBean")
    private MessageServiceRemote messagingService;
    
    public MessageResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(String content) {
        String message;
        StringReader reader = new StringReader(content);
        try (JsonReader jreader = Json.createReader(reader)) {
            JsonObject messageInfo = jreader.readObject();
            message = messageInfo.getString("message");
        }
        Boolean isValid = messagingService.verification(message);
        Response resp =null;
        if(isValid){
            resp = Response.accepted().build();
        }else{
            resp = Response.status(400).entity("message invalide").build();
        }
        return resp;
    }
}
