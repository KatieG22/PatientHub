package com.patienthub.config;

import com.patienthub.binding.TestBinder;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class TestAppConfig extends ResourceConfig {

    public TestAppConfig() {
        // this call has the same effect as
        // jersey.config.server.provider.packages
        // in the web.xml: it scans that packages for resources and providers.

        register(new TestBinder());
        packages("com.patienthub.api");
        register(ConstraintExceptionMapper.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);

    }

}
