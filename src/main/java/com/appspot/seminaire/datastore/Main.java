package com.appspot.seminaire.datastore;

import com.appspot.seminaire.datastore.routing.RoutesModule;
import com.appspot.seminaire.datastore.routing.RoutingInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class Main {

    public static void main(String[] args) {
        Injector routingInjector = Guice.createInjector(new RoutingInjector());
        RoutesModule app = routingInjector.getInstance(RoutesModule.class);
        app.setup();
    }
}
