package com.patienthub.webexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.patienthub.errors.ErrorMessage;

public class DuplicateData extends WebApplicationException {

    public DuplicateData() {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage("item exists in database"))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    public DuplicateData(String message) {

        super(Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(message)).type(MediaType.APPLICATION_JSON)
                .build());
    }

}
