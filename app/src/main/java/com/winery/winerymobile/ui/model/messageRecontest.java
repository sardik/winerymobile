package com.winery.winerymobile.ui.model;

import java.util.Collections;

public class messageRecontest {

    String id;
    String nik;
    String name;
    String tanggal;
    String total_banks;
    String bankss;
    String statusBanks;

    public messageRecontest(String id, String nik, String name, String tanggal,
                            String total_banks, String bankss, String statusBanks) {
        this.id = id;
        this.nik = nik;
        this.name = name;
        this.tanggal = tanggal;
        this.total_banks = total_banks;
        this.bankss = bankss;
        this.statusBanks = statusBanks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTotal_banks() {
        return total_banks;
    }

    public void setTotal_banks(String total_banks) {
        this.total_banks = total_banks;
    }

    public String getBankss() {
        return bankss;
    }

    public void setBankss(String bankss) {
        this.bankss = bankss;
    }

    public String getStatusBanks() {
        return statusBanks;
    }

    public void setStatusBanks(String statusBanks) {
        this.statusBanks = statusBanks;
    }

    public void sortAz(){

    }

}
