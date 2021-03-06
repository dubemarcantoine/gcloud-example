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

package com.appspot.gcloudExample.routing;

import com.appspot.gcloudExample.dao.DatabaseInjector;
import com.appspot.gcloudExample.routing.interfaces.IRouteConfig;
import com.appspot.gcloudExample.routing.interfaces.ITaskRoute;
import com.appspot.gcloudExample.routing.interfaces.IUserRoutes;
import com.appspot.gcloudExample.routing.sparkjava.RouteConfig;
import com.appspot.gcloudExample.routing.sparkjava.TaskRoutes;
import com.appspot.gcloudExample.routing.sparkjava.UserRoutes;
import com.google.inject.AbstractModule;

/**
 * Created by Marc-Antoine on 2016-05-18.
 */
public class RoutingInjector extends AbstractModule {

    @Override
    protected void configure() {
        this.install(new DatabaseInjector());
        //Bind the interfaces to implementation class
        bind(IRouteConfig.class).to(RouteConfig.class);
        bind(IUserRoutes.class).to(UserRoutes.class);
        bind(ITaskRoute.class).to(TaskRoutes.class);
    }
}
