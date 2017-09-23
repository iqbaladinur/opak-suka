package com.app.iqbaladinur.opacsuka.Model;

/**
 * Created by sanja on 03/06/2017.
 */

public class SavedData {
    int id;
    String judul, deskripsi, lokasi;

    public SavedData(){}
    public SavedData(int id, String j, String d, String l){
        this.setId(id);
        this.setJudul(j);
        this.setDes(d);
        this.setLokasi(l);
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

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
