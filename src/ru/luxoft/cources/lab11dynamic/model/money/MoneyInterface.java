package ru.luxoft.cources.lab11dynamic.model.money;

public interface MoneyInterface {
    void addMoney(Money money);
    Money getMoney(double balanceLess);
    Money getMoneyWithoutLess();

}