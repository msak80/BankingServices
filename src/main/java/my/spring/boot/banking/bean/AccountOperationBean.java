package my.spring.boot.banking.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class AccountOperationBean implements Serializable {
	private static final long serialVersionUID = -4946763786647449380L;
	
	@NotNull(message="Please enter email and password")
	private UserBean userBean;
	@NotNull(message="Please enter an amount")
	@Range(min=100)
	private double amount;
	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
