package com.app.iqbaladinur.opacsuka.Model;

/**
 * Created by sanja on 31/05/2017.
 */
import java.util.List;

import com.app.iqbaladinur.opacsuka.Model.Datum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPencarian {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
