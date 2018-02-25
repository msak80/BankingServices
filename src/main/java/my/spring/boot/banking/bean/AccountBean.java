package my.spring.boot.banking.bean;

import java.io.Serializable;
import java.util.List;

public class AccountBean implements Serializable {

	private static final long serialVersionUID = 2078975505992870723L;

	private String accountNumber;
	private Double balance;
	private List<TransactionBean> transactions;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<TransactionBean> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionBean> transactions) {
		this.transactions = transactions;
	}

}
