package ru.luxoft.cources.lab11dynamic.model.account;

import ru.luxoft.cources.lab11dynamic.model.score.Score;

import java.util.Map;

public class Account {
    private Principal principal;
    private String login;
    private String password;
    private Map<Integer, Score> scoreMap;

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScoreMap(Map<Integer, Score> scoreMap) {
        this.scoreMap = scoreMap;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Map<Integer, Score> getScoreMap() {
        return scoreMap;
    }

    public Account(Principal principal, String login, String password, Map<Integer, Score> scoreMap) {
        this.principal = principal;
        this.login = login;
        this.password = password;
        this.scoreMap = scoreMap;
    }
}
