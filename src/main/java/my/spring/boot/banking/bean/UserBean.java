package my.spring.boot.banking.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 3397186258421317313L;
	
	@NotNull(message="Please enter an email")
	@Email
	private String email;
	
	@NotNull(message="Please enter a password")
	@Length(min=6, max=20, message="Password should be between 6 - 20 charactes")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$", message="Password must has at least (a digit,a lower case letter,an upper case letter,a special character, and  no whitespace)")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
