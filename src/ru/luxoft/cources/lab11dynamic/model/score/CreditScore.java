package ru.luxoft.cources.lab11dynamic.model.score;

import ru.luxoft.cources.lab11dynamic.model.Loggable;
import ru.luxoft.cources.lab11dynamic.model.account.Account;
import ru.luxoft.cources.lab11dynamic.model.money.Money;

@Loggable
public class CreditScore extends Score {

    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }
}
