package ru.luxoft.cources.lab11dynamic.model;

import ru.luxoft.cources.lab11dynamic.model.constants.CurrencyHolder;
import ru.luxoft.cources.lab11dynamic.model.money.Money;
import ru.luxoft.cources.lab11dynamic.model.score.CreditScore;
import ru.luxoft.cources.lab11dynamic.model.score.CurrentScore;
import ru.luxoft.cources.lab11dynamic.model.score.DebetScore;
import ru.luxoft.cources.lab11dynamic.model.score.Score;

import java.lang.annotation.Annotation;
import java.util.EnumMap;

import static ru.luxoft.cources.lab11dynamic.model.ScoreTypeEnum.*;

public class ATM {
    private CurrentScore currentScore;
    private DebetScore debetScore;
    private CreditScore creditScore;

    private int operLimit;
    private int currentOpers;
    private boolean operLimitToggl;
    private EnumMap<ScoreTypeEnum, Score> scoresMap = new EnumMap<>(ScoreTypeEnum.class);

    public ATM() {
        this.creditScore =
                new CreditScore(
                        new Money(CurrencyHolder.getCurrencyByName("RUR"), 1000.0d), null, 1);

        this.debetScore =
                new DebetScore(
                        new Money(CurrencyHolder.getCurrencyByName("RUR"), 1000.0d), null, 1);
        this.currentScore =
                new CurrentScore(
                        new Money(CurrencyHolder.getCurrencyByName("RUR"), 1000.0d), null, 1);

        scoresMap.put(CREDIT, creditScore);
        scoresMap.put(DEBET, debetScore);
        scoresMap.put(CURRENT, currentScore);

        for (Annotation annotation :
                ATM.class.getAnnotations()) {
            if (annotation instanceof OperationLimitATM) {
                this.operLimit = ((OperationLimitATM) annotation).limit();
                this.operLimitToggl = true;
            }
        }
    }

    public void addMoneyToScore(Money money, ScoreTypeEnum choice) {
        if (operLimitToggl && currentOpers >= operLimit) {
            System.out.println("Operlimit ends!");
            return;
        }

        scoresMap.get(choice).addMoney(money);
        currentOpers++;
    }

    public Money getMoneyFromScore(Money money, ScoreTypeEnum choice) {
        if (operLimitToggl && currentOpers >= operLimit) {
            System.out.println("Operlimit ends!");
            return null;
        }
        currentOpers++;
        return scoresMap.get(choice).getMoney(money.getValue());
    }

    public CurrentScore getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(CurrentScore currentScore) {
        this.currentScore = currentScore;
    }

    public DebetScore getDebtScore() {
        return debetScore;
    }

    public void setDebitScore(DebetScore debetScore) {
        this.debetScore = debetScore;
    }

    public CreditScore getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(CreditScore creditScore) {
        this.creditScore = creditScore;
    }
}
