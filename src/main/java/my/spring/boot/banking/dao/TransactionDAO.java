package my.spring.boot.banking.dao;

import java.util.List;

import my.spring.boot.banking.entity.Transaction;

public interface TransactionDAO extends BaseDAO<Transaction, Long> {
	List<Transaction> findByAccountId(Long accountId);
}