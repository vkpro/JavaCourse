package ru.luxoft.cources.lab5core.model.score;

import ru.luxoft.cources.lab5core.model.account.Account;
import ru.luxoft.cources.lab5core.model.money.Money;
import ru.luxoft.cources.lab5core.model.money.MoneyInterface;

public abstract class Score implements MoneyInterface {
    private Money balance;
    private Account owner;
    private Integer number;

    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Money getBalance() {
        return balance;
    }

    public Account getOwner() {
        return owner;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public void addMoney(Money money) {
        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCource();

        if (usdValueThis < usdValueIn) {
            System.out.println("You have no so much!");
            return;
        }

        if (checkBefore()) {
            this.balance.setValue((usdValueThis + usdValueIn) * this.balance.getCurrency().getUsdCource());
        } else {
            System.out.println("No check!");
        }
    }

    @Override
    public Money getMoney(double balanceLess) {
        if (balanceLess > 30000) {
            throw new IllegalArgumentException("Wrong balance less!");
        }
        this.balance.setValue(this.balance.getValue() - balanceLess);
        return this.balance;
    }

    @Override
    public Money getMoneyWithoutLess() {
        return this.balance;
    }

    protected abstract boolean checkBefore();
}
