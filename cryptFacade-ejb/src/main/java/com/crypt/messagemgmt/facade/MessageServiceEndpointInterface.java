/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crypt.messagemgmt.facade;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author kazadri
 */
@WebService(name = "MessagingEndpoint")
public interface MessageServiceEndpointInterface {
    @WebMethod(operationName = "messageOperation")
    Boolean verification(@WebParam(name = "message")String message);
}
