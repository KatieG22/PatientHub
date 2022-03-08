package com.patienthub.config;

import com.patienthub.binding.AppBinder;
import com.patienthub.filters.CORSFilter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class AppConfig extends ResourceConfig {

    public AppConfig() {
        // this call has the same effect as
        // jersey.config.server.provider.packages
        // in the web.xml: it scans that packages for resources and providers.

        register(new AppBinder());
        packages("com.patienthub.api");
        // TODO: check why class isn't injecting
        // register(ValidationConfigurationContextResolver.class);
        register(ConstraintExceptionMapper.class);
        register(new CORSFilter());
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);

    }

}
