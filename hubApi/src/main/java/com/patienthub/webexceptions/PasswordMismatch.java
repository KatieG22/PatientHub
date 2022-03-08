package com.patienthub.webexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.errors.ErrorMessage;

public class PasswordMismatch extends WebApplicationException {


    public PasswordMismatch() {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("invalid credentials"))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }


    
}
