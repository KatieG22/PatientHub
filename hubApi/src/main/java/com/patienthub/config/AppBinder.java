package com.patienthub.config;

import com.patienthub.utils.DbConfig;
import com.patienthub.utils.ProdDbConfig;

import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

public class AppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(ProdDbConfig.class).to(DbConfig.class).in(PerLookup.class);

    }

}
