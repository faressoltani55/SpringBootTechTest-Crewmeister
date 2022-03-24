package com.crewmeister.cmcodingchallenge.controllers;

import com.crewmeister.cmcodingchallenge.models.EuroFx;
import com.crewmeister.cmcodingchallenge.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("/api")
public class CurrencyController {
    
    // Dependency injection of the service
    @Autowired
    CurrencyService currencyService;

    // As a client, I want to get a list of all available currencies
    @GetMapping("/currencies")
    public ResponseEntity<ArrayList<String>> getCurrencies() {
        return new ResponseEntity<>(currencyService.getAvailableCurrencies(), HttpStatus.OK);
    }

    // As a client, I want to get all EUR-FX exchange rates at all available dates as a collection
    @GetMapping("/exchange/history/all")
    public ResponseEntity<ArrayList<EuroFx>> getAllEuroFxExchangeRates() {
        return new ResponseEntity<>(currencyService.getAllEuroFxExchangeRates(), HttpStatus.OK);
    }

    // As a client, I want to get the EUR-FX exchange rate at particular day
    @GetMapping("/exchange/history/{date}")
    public ResponseEntity<ArrayList<EuroFx>> getAllEuroFxExchangeRatesForDate(@PathVariable("date") String date) {
        return new ResponseEntity<>(currencyService.getAllEuroFxExchangeRatesForDate(date), HttpStatus.OK);
    }

    // As a client, I want to get a foreign exchange amount for a given currency converted to EUR on a particular day
    @GetMapping("/exchange/{currency}/{date}")
    public ResponseEntity<Object> getAllEuroFxExchangeRatesForDate(@PathVariable("currency") String currency, @PathVariable("date") String date) {
        return new ResponseEntity<>(currencyService.getExchangeAmountForDate(currency, date), HttpStatus.OK);
    }
}
