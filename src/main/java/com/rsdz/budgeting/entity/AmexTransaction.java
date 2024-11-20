package com.rsdz.budgeting.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "amex")
public class AmexTransaction extends CCTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "category")
    private String category;

    @Column(name = "transaction_date")
    private String date;

    public AmexTransaction() {
        super();
    }

    public AmexTransaction(String amount, String category, String date) {
        super(amount, category, date);
        super.setId(this.id);
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
}
