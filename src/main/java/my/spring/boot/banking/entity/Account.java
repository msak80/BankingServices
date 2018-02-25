package my.spring.boot.banking.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ACCOUNTS")
@NamedQueries(value = {
		@NamedQuery(name = "findByAccountNumber", query = "from Account a where a.accountNumber = :accountNumber and a.active = true"),
		@NamedQuery(name = "findByUserId", query = "from Account a where a.userId = :userId and a.active = true"), })
public class Account extends BaseEntity {

	private static final long serialVersionUID = 5954589112128848641L;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "BALANCE")
	private Double balance;

	@Transient
	private List<Transaction> transactions;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}