package com.app.iqbaladinur.opacsuka.Model;

/**
 * Created by sanja on 31/05/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("des")
    @Expose
    private String des;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
