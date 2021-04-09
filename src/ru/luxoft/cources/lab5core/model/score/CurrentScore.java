package ru.luxoft.cources.lab5core.model.score;

import ru.luxoft.cources.lab5core.model.account.Account;
import ru.luxoft.cources.lab5core.model.money.Money;

public class CurrentScore extends Score{
    public CurrentScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }

    @Override
    public void addMoney(Money money) {
        if (money.getValue() > 1000000){
            Money moneyWithBonus = new Money(money.getCurrency(), money.getValue() + 2000);
            super.addMoney(moneyWithBonus);
        }
        super.addMoney(money);
    }
}
