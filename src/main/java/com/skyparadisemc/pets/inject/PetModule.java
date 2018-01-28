package com.skyparadisemc.pets.inject;

import com.google.inject.AbstractModule;
import com.skyparadisemc.pets.data.DataService;
import com.skyparadisemc.pets.data.JsonDataService;

public class PetModule extends AbstractModule {

    protected void configure() {
        bind(DataService.class).to(JsonDataService.class);
    }
}
