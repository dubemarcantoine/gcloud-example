package com.appspot.seminaire.datastore.routing.sparkjava;

import com.appspot.seminaire.datastore.routing.interfaces.IRouteConfig;

import static spark.Spark.*;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class RouteConfig implements IRouteConfig {

    public void init() {
        //The port where the application is accessible
        port(8080);

        //All requests result in json responses
        after((req, res) -> {
            res.type("application/json");
        });

    }
}
