package my.spring.boot.banking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import my.spring.boot.banking.bean.AccountBean;
import my.spring.boot.banking.bean.AccountOperationBean;
import my.spring.boot.banking.bean.TransactionBean;
import my.spring.boot.banking.bean.UserBean;
import my.spring.boot.banking.constant.Constant;
import my.spring.boot.banking.entity.Account;
import my.spring.boot.banking.entity.Transaction;
import my.spring.boot.banking.entity.TransactionAction;
import my.spring.boot.banking.entity.User;
import my.spring.boot.banking.service.AccountService;
import my.spring.boot.banking.service.UserService;
import my.spring.boot.banking.util.PasswordUtil;

@RestController
@RequestMapping("/banking")
public class BankingController {

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@Valid @RequestBody UserBean userBean, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter valid user details");
		}
		
		if (userService.getUserByEmail(userBean.getEmail()) != null) {
			return ResponseEntity.ok("This email address [" + userBean.getEmail() + "] is already in use");
		}

		try {
			User user = new User();
			user.setEmail(userBean.getEmail());
			user.setPassword(PasswordUtil.getHashedPassword(userBean.getPassword()));
			user.setActive(Constant.ACTIVE);
			user.setCreatedBy(Constant.SYSTEM_USER_ID);
			user.setCreatedOn(new Date());
			userService.addUser(user);

			return ResponseEntity.ok("The user [" + userBean.getEmail() + "] has added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Add user error");
		}
	}

	@RequestMapping(value = "/accounts/deposit", method = RequestMethod.POST)
	public ResponseEntity<String> dispute(@Valid @RequestBody AccountOperationBean accountOperationBean, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter valid account operation details");
		}
		
		if (accountOperationBean.getAmount() <= Constant.AMOUNT_ZERO) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter dispute withdraw amount");
		}
		
		User user = userService.getUserByEmail(accountOperationBean.getUserBean().getEmail());

		if (user == null || !user.getPassword().equals(PasswordUtil.getHashedPassword(accountOperationBean.getUserBean().getPassword()))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password are incorrect.");
		}

		try {
			accountService.dispute(user, accountOperationBean.getAmount());
			return ResponseEntity.ok("The transaction has done successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Dispute error");
		}
	}

	@RequestMapping(value = "/accounts/withdraw", method = RequestMethod.POST)
	public ResponseEntity<String> withdraw(@Valid @RequestBody AccountOperationBean accountOperationBean, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter valid account operation details");
		}
		
		if (accountOperationBean.getAmount() <= Constant.AMOUNT_ZERO) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter valid withdraw amount");
		}
		
		User user = userService.getUserByEmail(accountOperationBean.getUserBean().getEmail());

		if (user == null || !user.getPassword().equals(PasswordUtil.getHashedPassword(accountOperationBean.getUserBean().getPassword()))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password are incorrect.");
		}

		try {
			accountService.withdraw(user, accountOperationBean.getAmount());
			return ResponseEntity.ok("The transaction has done successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Withdraw error");
		}
	}

	@RequestMapping(value = "/accounts/details", method = RequestMethod.POST)
	public ResponseEntity<Object> getAccountDetails(@Valid @RequestBody UserBean userBean, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter valid user details");
		}
		
		User user = userService.getUserByEmail(userBean.getEmail());

		if (user == null || !user.getPassword().equals(PasswordUtil.getHashedPassword(userBean.getPassword()))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password are incorrect.");
		}

		try {
			Account account = accountService.getAccountDetails(user);

			AccountBean accountBean = new AccountBean();
			accountBean.setAccountNumber(account.getAccountNumber());
			accountBean.setBalance(account.getBalance());

			List<TransactionBean> transactionBeanList = new ArrayList<>();

			if (account.getTransactions() != null && !account.getTransactions().isEmpty()) {
				List<TransactionAction> transactionActions = accountService.getAllTransactionActions();
				
				for (Transaction transaction : account.getTransactions()) {
					TransactionBean transactionBean = new TransactionBean();
				
					transactionBean.setActionName(transactionActions.stream().filter(ta -> ta.getTransactionActionId().equals(transaction.getTransactionActionId())).findFirst().get().getTransactionActionName());
					transactionBean.setDate(Constant.SIMPLE_DATE_FORMAT.format(transaction.getCreatedOn()));
					transactionBean.setAmount(transaction.getAmount());
					transactionBeanList.add(transactionBean);
				}
			}
			accountBean.setTransactions(transactionBeanList);

			return ResponseEntity.ok(accountBean);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Get account details error");
		}
	}
}
