package my.spring.boot.banking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTIONS")
@NamedQueries(value = {
		@NamedQuery(name = "findByAccountId", query = "from Transaction t where t.accountId = :accountId and t.active = true"), })
public class Transaction extends BaseEntity {

	private static final long serialVersionUID = 989528015398714448L;

	@Column(name = "TRANSACTION_ACTION_ID")
	private Short transactionActionId;

	@Column(name = "ACCOUNT_ID")
	private Long accountId;

	@Column(name = "AMOUNT")
	private Double amount;

	public Short getTransactionActionId() {
		return transactionActionId;
	}

	public void setTransactionActionId(Short transactionActionId) {
		this.transactionActionId = transactionActionId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
