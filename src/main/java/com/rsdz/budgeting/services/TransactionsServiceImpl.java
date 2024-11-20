package com.rsdz.budgeting.services;

import com.rsdz.budgeting.dao.CCTransactionDao;
import com.rsdz.budgeting.dao.CCTransactionDaoImpl;
import com.rsdz.budgeting.entity.AmexTransaction;
import com.rsdz.budgeting.entity.CCTransaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionsServiceImpl implements TransactionService{
    CCTransactionDao ccTransactionDao;

    @Autowired
    public TransactionsServiceImpl(CCTransactionDao ccTransactionDao) {
        this.ccTransactionDao = ccTransactionDao;
    }

    @Override
    public List<? extends CCTransaction> findAll(){
        return ccTransactionDao.getAll();
    }

    @Override
    public CCTransaction findById(Integer id) {
        return ccTransactionDao.getByID(id);
    }

    @Transactional
    @Override
    public CCTransaction save(CCTransaction ccTransaction) {
        return ccTransactionDao.save(ccTransaction);
    }

    public void deleteTransactions() {
        ccTransactionDao.deleteAll();
    }
}
