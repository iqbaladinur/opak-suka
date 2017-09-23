package com.app.iqbaladinur.opacsuka.Interface;

/**
 * Created by sanja on 31/05/2017.
 */
import com.app.iqbaladinur.opacsuka.Model.DataPencarian;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Service {
    @GET("api/v3/search_key")
    Call<DataPencarian> getStatus(@Query("kata") String kata);

    @GET("api/v3/search_key")
    Call<DataPencarian> getData(@Query("kata") String kata);
}
