package com.appspot.seminaire.datastore.routing;

import com.appspot.seminaire.datastore.routing.interfaces.IRouteConfig;
import com.appspot.seminaire.datastore.routing.interfaces.IUserRoutes;
import com.appspot.seminaire.datastore.routing.sparkjava.RouteConfig;
import com.appspot.seminaire.datastore.routing.sparkjava.UserRoutes;
import com.google.inject.AbstractModule;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class RoutingInjector extends AbstractModule {

    @Override
    protected void configure() {
        //Bind the interfaces to implementation class
        bind(IRouteConfig.class).to(RouteConfig.class);
        bind(IUserRoutes.class).to(UserRoutes.class);
    }
}
