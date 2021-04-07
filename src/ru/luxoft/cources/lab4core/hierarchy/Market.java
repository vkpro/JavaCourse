package ru.luxoft.cources.lab4core.hierarchy;

import ru.luxoft.cources.lab4core.hierarchy.valut.Currency;

import java.util.List;

public class Market {
    private String name;
    private List<Currency> currencyList;
    private Emmitent emmitent;

    public Market(String name, Emmitent emmitent, List<Currency> currencyList) {
        this.name = name;
        this.emmitent = emmitent;
        this.currencyList = currencyList;
    }

    public Emmitent getEmmitent() {
        return emmitent;
    }

    public void setEmmitent(Emmitent emmitent) {
        this.emmitent = emmitent;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }


}
