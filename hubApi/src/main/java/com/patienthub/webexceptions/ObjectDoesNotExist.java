package com.patienthub.webexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.errors.ErrorMessage;

/**
 * Throw exception if object does not exist in database
 */
public class ObjectDoesNotExist extends WebApplicationException {

    public ObjectDoesNotExist() {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("item does not exist"))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    public ObjectDoesNotExist(String message) {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(message)).type(MediaType.APPLICATION_JSON)
                .build());
    }

}
