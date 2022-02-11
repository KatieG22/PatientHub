package com.patienthub.config;

import com.patienthub.utils.DbConfig;
import com.patienthub.utils.ProdDbConfig;

import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class AppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        // added ranking to help in mock overiding selection
        // src:
        // https://medium.com/@mnu/overriding-dependency-injection-with-hk2-in-jersey-tests-4756144c2399
        bind(ProdDbConfig.class).to(DbConfig.class).in(PerLookup.class);

    }

}
