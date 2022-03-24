package com.crewmeister.cmcodingchallenge.models;

import java.util.ArrayList;

public class EuroFx {
    String currency;
    ArrayList<CurrencyExchange> exchangeRates;

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setExchangeRates(ArrayList<CurrencyExchange> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public String getCurrency() {
        return currency;
    }

    public ArrayList<CurrencyExchange> getExchangeRates() {
        return exchangeRates;
    }
}
