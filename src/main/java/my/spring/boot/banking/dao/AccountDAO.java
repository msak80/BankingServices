package my.spring.boot.banking.dao;

import my.spring.boot.banking.entity.Account;

public interface AccountDAO extends BaseDAO<Account, Long> {
	Account findByAccountId(Long accountId);
	Account findByAccountNumber(String accountNumber);
	Account findByUserId(Long userId);
}