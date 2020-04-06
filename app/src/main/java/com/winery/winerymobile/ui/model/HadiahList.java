package com.winery.winerymobile.ui.model;

public class HadiahList {

    String id;
    String namaBarang;
    String jenis;
    String foto;

    public HadiahList(String id, String namaBarang, String jenis, String foto) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.jenis = jenis;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
