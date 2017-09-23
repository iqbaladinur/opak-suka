package com.app.iqbaladinur.opacsuka.Model;

import java.io.Serializable;

/**
 * Created by sanja on 31/05/2017.
 */

public class ListModel implements Serializable {
    String judul, deskripsi, lokasi;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDes() {
        return deskripsi;
    }

    public void setDes(String des) {
        this.deskripsi = des;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
