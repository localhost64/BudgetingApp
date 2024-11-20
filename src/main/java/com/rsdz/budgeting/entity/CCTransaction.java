package com.rsdz.budgeting.entity;

public class CCTransaction {
    private Integer id;
    private String amount;
    private String category;
    private String date;

    public CCTransaction(){

    }

    public CCTransaction(String amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "IZARTransaction{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
