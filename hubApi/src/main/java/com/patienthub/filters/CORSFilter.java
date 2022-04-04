package com.patienthub.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * @author Glen
 * @author Kate
 * @author Tumise
 * 
 *         The container runs before every response
 *         it ensures that all origins are allowed
 *         to avoid pre flight check issues with
 *         cause CORS errors.
 */
public class CORSFilter implements ContainerResponseFilter {

        @Override
        public void filter(ContainerRequestContext request, ContainerResponseContext response)

                        throws IOException {

                response.getHeaders().add("Access-Control-Allow-Origin", "*");
                response.getHeaders().add("Access-Control-Allow-Headers",
                                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
                response.getHeaders().add("Access-Control-Allow-Credentials", "true");
                response.getHeaders().add("Access-Control-Allow-Methods",
                                "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        }

}
