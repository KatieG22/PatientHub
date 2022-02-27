package com.patienthub.webexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.errors.ErrorMessage;

public class InvalidInput extends WebApplicationException {

    public InvalidInput() {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("invalid input"))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    public InvalidInput(String message) {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(message)).type(MediaType.APPLICATION_JSON)
                .build());
    }

}
