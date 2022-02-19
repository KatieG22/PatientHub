package com.patienthub.binding;

import com.patienthub.utils.DbConfig;
import com.patienthub.utils.DevDbConfig;

import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class TestBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(DevDbConfig.class).to(DbConfig.class).in(PerLookup.class);

    }
}
