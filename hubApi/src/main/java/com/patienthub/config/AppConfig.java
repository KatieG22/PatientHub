package com.patienthub.config;

import org.glassfish.jersey.server.ResourceConfig;
// import org.glassfish.jersey.server.ServerProperties;

public class AppConfig extends ResourceConfig {

    public AppConfig() {
        // this call has the same effect as
        // jersey.config.server.provider.packages
        // in the web.xml: it scans that packages for resources and providers.

        // register(org.glassfish.jersey.server.filter.UriConnegFilter.class);
        // register(org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainerProvider.class);
        // property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        packages("com.patienthub.api");
    }

}
