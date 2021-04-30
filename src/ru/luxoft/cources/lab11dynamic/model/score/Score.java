package ru.luxoft.cources.lab11dynamic.model.score;

import ru.luxoft.cources.lab11dynamic.model.Loggable;
import ru.luxoft.cources.lab11dynamic.model.MethodLimit;
import ru.luxoft.cources.lab11dynamic.model.account.Account;
import ru.luxoft.cources.lab11dynamic.model.money.Money;
import ru.luxoft.cources.lab11dynamic.model.money.MoneyInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Score implements MoneyInterface {
    private static final String LIMIT_IS_OVER_MSG = "Method limit is over!";
    private Money balance;
    private Account owner;
    private Integer number;

    private final  Map<String, Integer> methodConstraintMap;
    private final Map<String, Integer> methodCallMap;


    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;

        this.methodConstraintMap = new HashMap<>();
        this.methodCallMap = new HashMap<>();


        for (Method method : Score.class.getDeclaredMethods()) {
            MethodLimit limitAnnotation = method.getAnnotation(MethodLimit.class);
            if (limitAnnotation != null) {
                int limit = limitAnnotation.limit();
                methodConstraintMap.put(method.getName(), limit);
                methodCallMap.put(method.getName(), 0);
                boolean methodConstraintToggl = true;
            }
        }
    }

    public Money getBalance() {
        logIfneeded("getBalance");
        if (!isMethodAvailableByOperLimit("getBalance")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return balance;
    }

    public void setBalance(Money balance) {
        logIfneeded("setBalance");
        if (!isMethodAvailableByOperLimit("setBalance")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return;
        }
        this.balance = balance;
    }

    public Account getOwner() {
        logIfneeded("getOwner");
        if (!isMethodAvailableByOperLimit("getOwner")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return owner;
    }

    public void setOwner(Account owner) {
        logIfneeded("setOwner");
        if (!isMethodAvailableByOperLimit("setOwner")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return;
        }
        this.owner = owner;
    }

    public Integer getNumber() {
        logIfneeded("getNumber");
        if (!isMethodAvailableByOperLimit("getNumber")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return number;
    }

    public void setNumber(Integer number) {
        logIfneeded("setNumber");
        if (!isMethodAvailableByOperLimit("setNumber")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return;
        }
        this.number = number;
    }

    @Override
    public void addMoney(Money money) {
        logIfneeded("addMoney");
        if (!isMethodAvailableByOperLimit("addMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return;
        }
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
        logIfneeded("getMoney");
        if (!isMethodAvailableByOperLimit("getMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        if (balanceLess > 30000) {
            throw new IllegalArgumentException("Wrong balance less!");
        }
        this.balance.setValue(this.balance.getValue() - balanceLess);
        return this.balance;
    }

    @Override
    public Money getMoneyWithoutLess() {
        logIfneeded("getMoneyWithoutLess");
        if (!isMethodAvailableByOperLimit("getMoneyWithoutLess")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return this.balance;
    }

    protected abstract boolean checkBefore();

    protected void logIfneeded(String methodName) {
        for (Annotation annotation : this.getClass().getAnnotations()) {
            if (annotation instanceof Loggable) {
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isMethodAvailableByOperLimit(String methodName) {
        if (methodConstraintMap.containsKey(methodName)) {
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if (currentCalls >= limitCalls) {
                return false;
            }

            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return true;
    }

}
