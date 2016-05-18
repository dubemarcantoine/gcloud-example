/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Marc-Antoine Dubé
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.appspot.gcloudExample.dao.datastore;


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
