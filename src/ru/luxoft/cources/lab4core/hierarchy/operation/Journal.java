package ru.luxoft.cources.lab4core.hierarchy.operation;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private List<Operation> operations;


    public Journal() {
        operations = new ArrayList<Operation>();
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
