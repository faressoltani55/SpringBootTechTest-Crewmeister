package com.crewmeister.cmcodingchallenge.services;

import com.crewmeister.cmcodingchallenge.models.CurrencyExchange;
import com.crewmeister.cmcodingchallenge.models.EuroFx;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Service
public class CurrencyService {
    private final HashMap<String, String> currenciesFiles = new HashMap<>();
    private final ArrayList<String> availableCurrencies;

    // Manually linking the currencies and their files paths when the app starts
    public CurrencyService() {
        this.currenciesFiles.put("AUD", "src/main/resources/rates/BBEX3.D.AUD.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("BGN", "src/main/resources/rates/BBEX3.D.BGN.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("BRL", "src/main/resources/rates/BBEX3.D.BRL.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("CAD", "src/main/resources/rates/BBEX3.D.CAD.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("CHF", "src/main/resources/rates/BBEX3.D.CHF.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("CNY", "src/main/resources/rates/BBEX3.D.CNY.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("CYP", "src/main/resources/rates/BBEX3.D.CYP.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("CZK", "src/main/resources/rates/BBEX3.D.CZK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("DKK", "src/main/resources/rates/BBEX3.D.DKK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("EEK", "src/main/resources/rates/BBEX3.D.EEK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("GBP", "src/main/resources/rates/BBEX3.D.GBP.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("GRD", "src/main/resources/rates/BBEX3.D.GRD.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("HKD", "src/main/resources/rates/BBEX3.D.HKD.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("HRK", "src/main/resources/rates/BBEX3.D.HRK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("HUF", "src/main/resources/rates/BBEX3.D.HUF.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("IDR", "src/main/resources/rates/BBEX3.D.IDR.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("ILS", "src/main/resources/rates/BBEX3.D.ILS.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("INR", "src/main/resources/rates/BBEX3.D.INR.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("ISK", "src/main/resources/rates/BBEX3.D.ISK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("JPY", "src/main/resources/rates/BBEX3.D.JPY.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("KRW", "src/main/resources/rates/BBEX3.D.KRW.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("LTL", "src/main/resources/rates/BBEX3.D.LTL.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("LVL", "src/main/resources/rates/BBEX3.D.LVL.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("MTL", "src/main/resources/rates/BBEX3.D.MTL.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("MXN", "src/main/resources/rates/BBEX3.D.MXN.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("MYR", "src/main/resources/rates/BBEX3.D.MYR.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("NOK", "src/main/resources/rates/BBEX3.D.NOK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("NZD", "src/main/resources/rates/BBEX3.D.NZD.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("PHP", "src/main/resources/rates/BBEX3.D.PHP.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("PLN", "src/main/resources/rates/BBEX3.D.PHP.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("ROL", "src/main/resources/rates/BBEX3.D.ROL.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("RON", "src/main/resources/rates/BBEX3.D.RON.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("RUB", "src/main/resources/rates/BBEX3.D.RUB.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("SEK", "src/main/resources/rates/BBEX3.D.SEK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("SGD", "src/main/resources/rates/BBEX3.D.SGD.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("SIT", "src/main/resources/rates/BBEX3.D.SIT.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("SKK", "src/main/resources/rates/BBEX3.D.SKK.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("THB", "src/main/resources/rates/BBEX3.D.THB.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("TRL", "src/main/resources/rates/BBEX3.D.TRL.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("TRY", "src/main/resources/rates/BBEX3.D.TRY.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("USD", "src/main/resources/rates/BBEX3.D.USD.EUR.BB.AC.000.csv");
        this.currenciesFiles.put("ZAR", "src/main/resources/rates/BBEX3.D.ZAR.EUR.BB.AC.000.csv");
        // Saving available currencies
        this.availableCurrencies = new ArrayList<>(currenciesFiles.keySet());
    }

    // Get available currency
    public ArrayList<String> getAvailableCurrencies() {
        return this.availableCurrencies;
    }

    // Get all EUR-FX exchange rates at all available dates as a collection
    public ArrayList<EuroFx> getAllEuroFxExchangeRates() {

        ArrayList<EuroFx> exchangeRates = new ArrayList<>();

        // For each currency
        currenciesFiles.forEach((currency, filePath) -> {
            // Declare the currency history
            EuroFx currencyHistory = new EuroFx();
            currencyHistory.setCurrency(currency);

            // Declare a list to contain our data rows
            ArrayList<CurrencyExchange> history = new ArrayList<>();

            try {
                // Use a bufferedReader to read the file
                BufferedReader br = new BufferedReader(new FileReader(filePath));

                int verboseLines = 0;
                String line;

                while ((line = br.readLine()) != null) {
                    // There are 8 explanatory lines in each file
                    if (verboseLines > 8) {
                        // Save the currency exchange rate if available
                        String[] values = line.split(",");
                        if(values[1].equals("."))
                            values[1] = "Not Available";
                        history.add(new CurrencyExchange(values[0], values[1]));
                    } else {
                        verboseLines++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // building the collection
            currencyHistory.setExchangeRates(history);
            exchangeRates.add(currencyHistory);

        });

        return exchangeRates;
    }

    // Get the EUR-FX exchange rate at particular day
    public ArrayList<EuroFx> getAllEuroFxExchangeRatesForDate(String date) {

        ArrayList<EuroFx> exchangeRates = new ArrayList<>();

        // For each currency
        currenciesFiles.forEach((currency, filePath) -> {

            // Declare the currency history
            EuroFx currencyHistory = new EuroFx();
            currencyHistory.setCurrency(currency);
            ArrayList<CurrencyExchange> history = new ArrayList<>();

            try {

                BufferedReader br = new BufferedReader(new FileReader(filePath));
                int verboseLines = 0;
                String line;
                CurrencyExchange currencyExchange = null;

                while ((line = br.readLine()) != null) {
                    if (verboseLines >= 9) {
                        String[] values = line.split(",");
                        if (date.equals(values[0]))
                            if (!values[1].equals("."))
                                currencyExchange = new CurrencyExchange(values[0], values[1]);
                            else
                                currencyExchange = new CurrencyExchange(values[0], "Not Available");
                    } else {
                        verboseLines++;
                    }
                }

                history.add(currencyExchange);

            } catch (Exception e) {
                e.printStackTrace();
            }

            // If we don't have a currency conversion value for that date we fill it with not available
            if(history.get(0) == null) {
                history.set(0, new CurrencyExchange(date, "Not Available"));
            }

            currencyHistory.setExchangeRates(history);
            exchangeRates.add(currencyHistory);

        });

        return exchangeRates;
    }

    // Get a foreign exchange amount for a given currency converted to EUR on a particular day
    public Object getExchangeAmountForDate(String currency, String date) {

        // Verify that we have the currency
        if(this.availableCurrencies.contains(currency)) {

            String filePath = this.currenciesFiles.get(currency);
            Object exchangeRate = null;

            try {
                BufferedReader br = new BufferedReader(new FileReader(filePath));

                int verboseLines = 0;
                String line;

                while ((line = br.readLine()) != null) {
                    if (verboseLines >= 9) {
                        String[] values = line.split(",");
                        if (date.equals(values[0]))
                            exchangeRate = values[1];
                    } else {
                        verboseLines++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (exchangeRate == null)
                return "Currency Not Available For the Given Date";

            return exchangeRate;
        }

        else {
            return "Currency Not Available";
        }
    }

}
