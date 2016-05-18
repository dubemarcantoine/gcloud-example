package com.appspot.seminaire.datastore.dao.datastore;


import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

/**
 * Created by Marc-Antoine on 2016-03-25.
 */
public class Connection {

    private static Datastore client;

    private Connection() {
        connect();
    }

    private static void connect() {
        try {
            //METHOD 1
            client = DatastoreOptions.defaultInstance().service();
//            //METHOD 2
//            DatastoreOptions options = DatastoreOptions.builder()
//                    .projectId("PROJECT_NAME")
//                    .authCredentials(AuthCredentials.createApplicationDefaults()).build();
//            client = options.service();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Datastore getConn() {
        if(client == null) {
            new Connection();
        }
        return client;
    }
}
