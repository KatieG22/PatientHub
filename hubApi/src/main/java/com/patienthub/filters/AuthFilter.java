package com.patienthub.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.patienthub.annotation.Secured;
import com.patienthub.errors.ErrorMessage;
import com.patienthub.service.AuthService;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {
    private static final String REALM = "Access to protected resource";
    private static final String CHARSET = "UTF-8";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    private class NotAuthorized extends WebApplicationException {
        public NotAuthorized() {

            super(
                    Response.status(Status.UNAUTHORIZED)
                            .entity(new ErrorMessage("Permission Denied"))
                            .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " " + "realm=" + REALM)
                            .type(MediaType.APPLICATION_JSON).build());

        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // TODO Auto-generated method stub

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (!hasToken(authorizationHeader)) {
            throw new NotAuthorized();
        }

        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();
        AuthService authService = new AuthService();

        if (!authService.validToken(token)) {
            throw new NotAuthorized();
        }

    }

    private boolean hasToken(String authorizationHeader) {
        // is a token present and does it start with Bearer
        return authorizationHeader != null
                && authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + "");
    }

}
