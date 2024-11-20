package com.rsdz.budgeting.dao;

import com.rsdz.budgeting.entity.AmexTransaction;
import com.rsdz.budgeting.entity.CCTransaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CCTransactionDaoImpl implements CCTransactionDao{
    private EntityManager entityManager;

    @Autowired
    public CCTransactionDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<? extends CCTransaction> getAll() {
        TypedQuery<AmexTransaction> query =
                entityManager.createQuery("from AmexTransaction", AmexTransaction.class);

        return query.getResultList();
    }

    @Override
    public CCTransaction getByID(Integer id) {
        CCTransaction dbTransaction = entityManager.find(AmexTransaction.class, id);
        return dbTransaction;
    }
    @Override
    public CCTransaction save(CCTransaction ccTransaction) {
        CCTransaction dbTransaction = entityManager.merge(ccTransaction);
        return dbTransaction;
    }

    @Transactional
    @Override
    public void saveAll(List<? extends CCTransaction> ccTransactionList) {
        if(ccTransactionList != null) {
            for(CCTransaction transaction : ccTransactionList) {
                this.save(transaction);
            }
        }
    }
    @Transactional
    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM AmexTransaction").executeUpdate();
    }
}
