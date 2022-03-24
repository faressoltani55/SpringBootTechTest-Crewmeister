package com.crewmeister.cmcodingchallenge.models;


public class CurrencyExchange {
    String date;
    String value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CurrencyExchange(String date, String value) {
        this.date = date;
        this.value = value;
    }
}
