package com.appspot.seminaire.datastore.routing.sparkjava;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Marc-Antoine on 2016-03-19.
 */
public class JsonResponse {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonResponse::toJson;
    }
}
