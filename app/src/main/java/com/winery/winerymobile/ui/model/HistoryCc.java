package com.winery.winerymobile.ui.model;

public class HistoryCc {

    String id;
    String dateInput;
    String name;
    String nik;
    String tanggal_lahir;

    public HistoryCc(String id, String dateInput, String name, String nik, String tanggal_lahir) {
        this.id = id;
        this.dateInput = dateInput;
        this.name = name;
        this.nik = nik;
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
}
