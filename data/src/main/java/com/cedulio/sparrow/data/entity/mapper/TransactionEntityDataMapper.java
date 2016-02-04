package com.cedulio.sparrow.data.entity.mapper;

import com.cedulio.sparrow.data.entity.TransactionEntity;
import com.cedulio.sparrow.domain.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransactionEntityDataMapper {

    public Transaction transform(TransactionEntity transactionEntity) {
        Transaction transaction = null;

        if (transactionEntity != null) {
            transaction = new Transaction();
            transaction.setAmount(transactionEntity.getAmount());
            transaction.setCharges(transactionEntity.getCharges());
            transaction.setHref(transactionEntity.getHref());
            transaction.setIndex(transactionEntity.getIndex());
            transactionEntity.setPostDate(transactionEntity.getPostDate());
            transaction.setTitle(transactionEntity.getTitle());
        }

        return transaction;
    }

    public List<Transaction> transform(Collection<TransactionEntity> transactionEntityCollection) {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction;

        for (TransactionEntity transactionEntity : transactionEntityCollection) {
            transaction = transform(transactionEntity);
            if (transaction != null) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

}
