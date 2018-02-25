package my.spring.boot.banking.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import my.spring.boot.banking.dao.AccountDAO;
import my.spring.boot.banking.entity.Account;

@Repository
public class AccountDAOImpl extends BaseDAOImpl<Account, Long> implements AccountDAO {

	@Override
	public Account findByAccountId(Long accountId) {
		return findById(Account.class, accountId);
	}

	@Override
	public Account findByAccountNumber(String accountNumber) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("accountNumber", accountNumber);
		return findByNamedQuerySingleResult("findByAccountNumber", parameters);
	}

	@Override
	public Account findByUserId(Long userId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userId", userId);
		return findByNamedQuerySingleResult("findByUserId", parameters);
	}
}
