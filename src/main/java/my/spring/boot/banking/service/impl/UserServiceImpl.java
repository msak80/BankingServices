package my.spring.boot.banking.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.spring.boot.banking.constant.Constant;
import my.spring.boot.banking.dao.AccountDAO;
import my.spring.boot.banking.dao.UserDAO;
import my.spring.boot.banking.entity.Account;
import my.spring.boot.banking.entity.User;
import my.spring.boot.banking.service.UserService;
import my.spring.boot.banking.util.BankingUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AccountDAO accountDAO;

	@Override
	public void addUser(User user) {
		userDAO.save(user);
		Account account = new Account();
		account.setUserId(user.getId());
		account.setCreatedBy(user.getId());
		account.setCreatedOn(new Date());
		account.setAccountNumber(BankingUtil.getAccountNumber(user.getId()));	
		account.setBalance(Constant.AMOUNT_ZERO);
		account.setActive(Constant.ACTIVE);
		accountDAO.save(account);
	}


	@Override
	public void updateUser(User user) {
		userDAO.update(user);
		
	}

	@Override
	public User getUserByUserId(Long userId) {
		return userDAO.findById(User.class, userId);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}

}
