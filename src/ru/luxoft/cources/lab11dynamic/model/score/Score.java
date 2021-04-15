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
    private Money balance;
    private Account owner;
    private Integer number;

    private Map<String, Integer> methodConstraintMap;
    private Map<String, Integer> methodCallMap;


    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;

        this.methodConstraintMap = new HashMap<String, Integer>();
        this.methodCallMap = new HashMap<String, Integer>();


        Class thisClass = this.getClass();
        for (Method method:
                thisClass.getDeclaredMethods()) {
            for (Annotation annotation:
                    method.getDeclaredAnnotations()) {
                if(annotation instanceof MethodLimit){
                    int limit = ((MethodLimit)annotation).limit();
                    methodConstraintMap.put(method.getName(), limit);
                    methodCallMap.put(method.getName(), 0);
                    boolean methodConstraintToggl = true;
                }
            }
        }

    }

    public void setBalance(Money balance) {
        logIfneeded("setBalance");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return;
        }
        this.balance = balance;
    }

    public void setOwner(Account owner) {
        logIfneeded("setOwner");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return;
        }
        this.owner = owner;
    }

    public void setNumber(Integer number) {
        logIfneeded("setNumber");
        if(!isMethodAvailableByOperLimit("setNumber")){
            System.out.println("Method limit is over!");
            return;
        }
        this.number = number;
    }

    public Money getBalance() {
        logIfneeded("getBalance");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return null;
        }
        return balance;
    }

    public Account getOwner() {
        logIfneeded("getOwner");
        if(!isMethodAvailableByOperLimit("getOwner")){
            System.out.println("Method limit is over!");
            return null;
        }
        return owner;
    }

    public Integer getNumber() {
        logIfneeded("getNumber");
        if(!isMethodAvailableByOperLimit("getNumber")){
            System.out.println("Method limit is over!");
            return null;
        }
        return number;
    }

    @Override
    public void addMoney(Money money) {
        logIfneeded("addMoney");
        if(!isMethodAvailableByOperLimit("addMoney")){
            System.out.println("Method limit is over!");
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
        if(!isMethodAvailableByOperLimit("getMoney")){
            System.out.println("Method limit is over!");
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
        if(!isMethodAvailableByOperLimit("getMoneyWithoutLess")){
            System.out.println("Method limit is over!");
            return null;
        }
        return this.balance;
    }

    protected abstract boolean checkBefore();

    protected void logIfneeded(String methodName){
        Class thisClass = this.getClass();
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof Loggable){
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isMethodAvailableByOperLimit(String methodName){
        if(methodConstraintMap.containsKey(methodName)){
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if(currentCalls >= limitCalls){
                return false;
            }

            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return true;
    }

}
