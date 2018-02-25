package my.spring.boot.banking.bean;

import java.io.Serializable;

public class TransactionBean implements Serializable {
	private static final long serialVersionUID = 4058832939473914860L;
	private String actionName;
	private Double amount;
	private String date;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
