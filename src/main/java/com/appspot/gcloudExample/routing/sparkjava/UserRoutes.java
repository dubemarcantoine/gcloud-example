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

package com.appspot.gcloudExample.routing.sparkjava;

import com.appspot.gcloudExample.dao.DatabaseModule;
import com.appspot.gcloudExample.dao.DatabaseInjector;
import com.appspot.gcloudExample.routing.interfaces.IUserRoutes;
import com.google.inject.Guice;
import com.google.inject.Injector;
import spark.Spark;

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

        Spark.get("/users", (req, res) -> {
            return db.getUsers();
        }, JsonResponse.json());
    }
}
