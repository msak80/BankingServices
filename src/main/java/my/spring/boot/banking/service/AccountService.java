package my.spring.boot.banking.service;

import java.util.List;

import my.spring.boot.banking.entity.Account;
import my.spring.boot.banking.entity.TransactionAction;
import my.spring.boot.banking.entity.User;

public interface AccountService {
	void dispute(User user,double amount) ;
	void withdraw(User user,double amount) ;
	Account getAccountDetails(User user) ;
	List<TransactionAction> getAllTransactionActions();
}
