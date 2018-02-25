package my.spring.boot.banking.service;

import my.spring.boot.banking.entity.Transaction;

public interface TransactionService {

	void addTransaction(Transaction transaction);

	void updateTransaction(Transaction transaction);

	Transaction getTransactionByTransactionId(Long transactionId);

	void getUserByAccountNumber(String accountNumber);
}
