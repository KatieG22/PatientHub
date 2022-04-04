package com.patienthub.webexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.patienthub.errors.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Throw class execption if user object does not
 * exist in database
 */
public class UserDoesNotExist extends WebApplicationException {

    public UserDoesNotExist() {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("user does not exist"))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    public UserDoesNotExist(String message) {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(message)).type(MediaType.APPLICATION_JSON)
                .build());
    }

}
