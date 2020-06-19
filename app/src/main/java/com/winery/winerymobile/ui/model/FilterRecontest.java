package com.winery.winerymobile.ui.model;

public class FilterRecontest {
    String Bank;
    String rjVerif;

    public FilterRecontest(String bank, String rjVerif) {
        Bank = bank;
        this.rjVerif = rjVerif;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getRjVerif() {
        return rjVerif;
    }

    public void setRjVerif(String rjVerif) {
        this.rjVerif = rjVerif;
    }
}
