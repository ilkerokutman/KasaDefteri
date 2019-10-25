package com.performans.io.kasadefteri;

public class ExpenseModel {

    private int id;
    private String description;
    private double amount;
    private String date;
    private boolean isExpense;


    public ExpenseModel(double amount, String description, boolean isExpense) {
        this.amount = amount;
        this.description = description;
        this.isExpense = isExpense;
        this.date = Utility.getNow();
    }

    public ExpenseModel(double amount, String description, boolean isExpense, String date) {
        this.amount = amount;
        this.description = description;
        this.isExpense = isExpense;
        this.date = date;
    }

    public ExpenseModel(int id, double amount, String description, boolean isExpense, String date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.isExpense = isExpense;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}
