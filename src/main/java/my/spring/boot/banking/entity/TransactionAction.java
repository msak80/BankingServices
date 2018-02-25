package my.spring.boot.banking.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTIONS_ACTIONS")
public class TransactionAction implements Serializable {
	private static final long serialVersionUID = 3946066190945915578L;

	@Id
	@Basic(optional = false)
	@Column(name = "TRANSACTION_ACTION_ID", nullable = false)
	private Short transactionActionId;

	@Column(name = "TRANSACTION_ACTION_NAME")
	private String transactionActionName;

	@Column(name = "TRANSACTION_ACTION_EN_NAME")
	private String transactionActionEnName;

	public TransactionAction() {
	}
	
	public TransactionAction(Short transactionActionId) {
		this.transactionActionId = transactionActionId;
	}
	
	public Short getTransactionActionId() {
		return transactionActionId;
	}

	public void setTransactionActionId(Short transactionActionId) {
		this.transactionActionId = transactionActionId;
	}

	public String getTransactionActionName() {
		return transactionActionName;
	}

	public void setTransactionActionName(String transactionActionName) {
		this.transactionActionName = transactionActionName;
	}

	public String getTransactionActionEnName() {
		return transactionActionEnName;
	}

	public void setTransactionActionEnName(String transactionActionEnName) {
		this.transactionActionEnName = transactionActionEnName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionActionId == null) ? 0 : transactionActionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionAction other = (TransactionAction) obj;
		if (transactionActionId == null) {
			if (other.transactionActionId != null)
				return false;
		} else if (!transactionActionId.equals(other.transactionActionId))
			return false;
		return true;
	}
}
