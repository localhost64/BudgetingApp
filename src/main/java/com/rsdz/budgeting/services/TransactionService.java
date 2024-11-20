package com.rsdz.budgeting.services;

import com.rsdz.budgeting.entity.AmexTransaction;
import com.rsdz.budgeting.entity.CCTransaction;

import java.util.List;

public interface TransactionService {
    List<? extends CCTransaction> findAll();

    CCTransaction findById(Integer id);

    CCTransaction save(CCTransaction ccTransaction);
}
