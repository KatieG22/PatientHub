package com.patienthub.webexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.errors.ErrorMessage;

public class FieldRequired extends WebApplicationException {

    public FieldRequired() {
        super(
                Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("field required"))
                        .type(MediaType.APPLICATION_JSON).build());
    }

    public FieldRequired(String message) {
        super(
                Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(message))
                        .type(MediaType.APPLICATION_JSON).build());
    }

}
