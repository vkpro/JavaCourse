package ru.luxoft.cources.lab5core.model.money;

public class Currency {
    private String name;
    private float usdCource;

    public void setName(String name) {
        this.name = name;
    }

    public void setUsdCource(float usdCource) {
        this.usdCource = usdCource;
    }

    public String getName() {
        return name;
    }

    public float getUsdCource() {
        return usdCource;
    }

    public Currency(String name, float usdCource) {
        this.name = name;
        this.usdCource = usdCource;
    }
}
