package ru.luxoft.cources.lab5core.model.constants;

import ru.luxoft.cources.lab5core.model.money.Currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyHolder {
    private CurrencyHolder() {
    }

    private static final Map<String, Currency> currencies = new HashMap<>();
    static {
        currencies.put("USD",new Currency("USD", 1));
        currencies.put("RUR",new Currency("RUR", 65.5f));
    }

    public static Currency getCurrencyByName(String name) {
        return currencies.get(name);
    }
}

