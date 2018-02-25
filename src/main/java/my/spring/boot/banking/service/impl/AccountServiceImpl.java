package my.spring.boot.banking.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.spring.boot.banking.constant.Constant;
import my.spring.boot.banking.constant.TransactionActionEnum;
import my.spring.boot.banking.dao.AccountDAO;
import my.spring.boot.banking.dao.TransactionActionDAO;
import my.spring.boot.banking.dao.TransactionDAO;
import my.spring.boot.banking.entity.Account;
import my.spring.boot.banking.entity.Transaction;
import my.spring.boot.banking.entity.TransactionAction;
import my.spring.boot.banking.entity.User;
import my.spring.boot.banking.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDAO accountDAO;

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	TransactionActionDAO transactionActionDAO;

	@Override
	public void dispute(User user, double amount) {
		Account account = accountDAO.findByUserId(user.getId());

		if (account != null && user.getId().equals(account.getUserId())) {
			Date now = new Date();

			Transaction transaction = new Transaction();
			transaction.setTransactionActionId(TransactionActionEnum.DISPUTE.getValue());
			transaction.setAmount(amount);
			transaction.setAccountId(account.getId());
			transaction.setActive(Constant.ACTIVE);
			transaction.setCreatedBy(user.getId());
			transaction.setCreatedOn(now);

			transactionDAO.save(transaction);
			account.setBalance(account.getBalance() + amount);
			accountDAO.save(account);
		} else {
			throw new RuntimeException("Access denied error");
		}

	}

	@Override
	public void withdraw(User user, double amount) {
		Account account = accountDAO.findByUserId(user.getId());

		if (account != null && user.getId().equals(account.getUserId())
				&& account.getBalance().doubleValue() >= amount) {

			Date now = new Date();

			Transaction transaction = new Transaction();
			transaction.setTransactionActionId(TransactionActionEnum.WITHDRAW.getValue());
			transaction.setAmount(amount);
			transaction.setAccountId(account.getId());
			transaction.setActive(Constant.ACTIVE);
			transaction.setCreatedBy(user.getId());
			transaction.setCreatedOn(now);

			transactionDAO.save(transaction);
			account.setBalance(account.getBalance() - amount);
			accountDAO.save(account);
		} else {
			throw new RuntimeException("No enough balance error, or access denied error");
		}
	}

	@Override
	public Account getAccountDetails(User user) {
		Account account = accountDAO.findByUserId(user.getId());
		account.setTransactions(transactionDAO.findByAccountId(account.getId()));
		return account;
	}

	@Override
	public List<TransactionAction> getAllTransactionActions() {
		return transactionActionDAO.findAll(TransactionAction.class);
	}
}
