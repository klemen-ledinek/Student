package com.example;

public class Dogodek {
    private String ime;
    private String datum;
    private String ura;
    private int tip;

    public Dogodek(){

    }

    public Dogodek(String ime, String datum, String ura, int tip) {
        this.ime = ime;
        this.datum = datum;
        this.ura = ura;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Dogodek{" +
                "ime='" + ime + '\'' +
                ", datum=" + datum +
                ", ura=" + ura +
                '}';
    }



    public String getIme() {
        return ime;
    }

    public String getDatum() {
        return datum;
    }

    public String getUra() {
        return ura;
    }
    public int getTip () {return tip;}

    public void setTip (int tip){this.tip = tip;}
    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setUra(String ura) {
        this.ura = ura;
    }
}

