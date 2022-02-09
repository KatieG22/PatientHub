package com.patienthub.config;

import org.glassfish.jersey.server.ResourceConfig;
// import org.glassfish.jersey.server.ServerProperties;

public class AppConfig extends ResourceConfig {

    public AppConfig() {
        // this call has the same effect as
        // jersey.config.server.provider.packages
        // in the web.xml: it scans that packages for resources and providers.

        register(new AppBinder());
        packages("com.patienthub.api");

        ;

    }

}
