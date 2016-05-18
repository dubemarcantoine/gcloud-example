package com.appspot.seminaire.datastore.routing;

import com.appspot.seminaire.datastore.routing.interfaces.IRouteConfig;
import com.appspot.seminaire.datastore.routing.interfaces.IUserRoutes;
import com.google.inject.Inject;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class RoutesModule {

    private IRouteConfig routeConfig;
    private IUserRoutes userRoutes;

    @Inject
    public RoutesModule(IRouteConfig routeConfig, IUserRoutes userRoutes) {
        this.routeConfig = routeConfig;
        this.userRoutes = userRoutes;
    }

    public void setup() {
        this.routeConfig.init();
        this.userRoutes.listen();
    }
}
