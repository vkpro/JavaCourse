package ru.luxoft.cources.lab5core.model.score;

import ru.luxoft.cources.lab5core.model.account.Account;
import ru.luxoft.cources.lab5core.model.money.Money;

public class CreditScore extends Score {

    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }
}
