package com.example;

/**
 * Created by Ledo on 25. 05. 2016.
 */
public class Student {
    private String email;
    private String ime;
    private String priimek;


    public Student(String email, String ime, String priimek) {
        this.email = email;
        this.ime = ime;
        this.priimek = priimek;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", priimek='" + priimek + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }
}
