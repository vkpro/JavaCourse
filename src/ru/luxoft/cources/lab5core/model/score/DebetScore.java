package ru.luxoft.cources.lab5core.model.score;

import ru.luxoft.cources.lab5core.model.account.Account;
import ru.luxoft.cources.lab5core.model.money.Money;

public class DebetScore extends Score {
    private CreditScore creditScore;

    public DebetScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    protected boolean checkBefore() {
        return true;
    }

    @Override
    public void addMoney(Money money) {
        if (creditScore.getBalance().getValue() < 20000) {
            super.addMoney(money);
        }
    }
}
