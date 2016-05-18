package com.appspot.seminaire.datastore.routing.sparkjava;

import com.appspot.seminaire.datastore.dao.DatabaseModule;
import com.appspot.seminaire.datastore.dao.DatabaseInjector;
import com.appspot.seminaire.datastore.routing.interfaces.IUserRoutes;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static com.appspot.seminaire.datastore.routing.sparkjava.JsonResponse.json;
import static spark.Spark.get;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class UserRoutes implements IUserRoutes {

    private DatabaseModule db;

    public void listen() {
        //Inject database
        Injector databaseInjector = Guice.createInjector(new DatabaseInjector());
        db = databaseInjector.getInstance(DatabaseModule.class);

        get("/users", (req, res) -> {
            return db.getUsers();
        }, json());
    }
}
