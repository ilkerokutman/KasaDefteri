package com.performans.io.kasadefteri;

import java.util.Date;

public class ExpenseModel {

    int id;
    String description;
    double amount;
    Date date;
    boolean isExpense;


    public ExpenseModel(double amount, String description, boolean isExpense) {
        this.amount = amount;
        this.description = description;
        this.isExpense = isExpense;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
