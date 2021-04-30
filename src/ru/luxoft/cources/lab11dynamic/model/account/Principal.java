package ru.luxoft.cources.lab11dynamic.model.account;

public class Principal {
    private String firstName;
    private String lastName;
    private String secondName;
    private short age;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public short getAge() {
        return age;
    }

    public Principal(String firstName, String lastName, String secondName, short age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.age = age;
    }
}
