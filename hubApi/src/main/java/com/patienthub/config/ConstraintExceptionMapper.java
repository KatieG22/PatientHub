
package com.patienthub.config;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * class handles how constraint violations are displayed to user.
 * 
 * Registered class in app config to enable dependency injection
 * at run time.
 * 
 * Every exception mapper must implient a toResponse method.
 * prepareMessage is an helper function. we took advantage of the
 * in build ValidationError class as described in the documentation
 * https://eclipse-ee4j.github.io/jersey.github.io/apidocs/snapshot/jersey/org/glassfish/jersey/server/validation/ValidationError.html
 */
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                .entity(prepareMessage(exception))
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    private List<ConstraintError> prepareMessage(ConstraintViolationException exception) {
        ArrayList<ConstraintError> errors = new ArrayList<>();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            String msg = cv.getMessage();
            String propertyPath = cv.getPropertyPath().toString();
            ConstraintError error = new ConstraintError();
            error.setMessage(msg);
            // property path should display as field name
            error.setDetail(propertyPath);
            errors.add(error);
        }
        return errors;
    }

}