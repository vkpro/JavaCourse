package ru.luxoft.cources.lab11dynamic.model.money;

public class Money {
    private Currency currency;
    private double value;

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    public Money(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }
}
