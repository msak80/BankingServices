package my.spring.boot.banking.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import my.spring.boot.banking.dao.TransactionDAO;
import my.spring.boot.banking.entity.Transaction;

@Repository
public class TransactionDAOImpl extends BaseDAOImpl<Transaction, Long> implements TransactionDAO {

	@Override
	public List<Transaction> findByAccountId(Long accountId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("accountId", accountId);
		return findByNamedQuery("findByAccountId", parameters);
	}
}