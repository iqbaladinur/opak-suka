package com.app.iqbaladinur.opacsuka.HttpRestHandler;

import com.app.iqbaladinur.opacsuka.HttpRestHandler.RetrofitClient;
import com.app.iqbaladinur.opacsuka.Interface.Service;

/**
 * Created by sanja on 31/05/2017.
 */

public class ApiUtils {
    //public static final String BASE_URL = "http://opac-api.azurewebsites.net/search_key/";

    public static Service getService(String BASE_URL) {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
