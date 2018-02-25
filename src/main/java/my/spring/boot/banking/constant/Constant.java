package my.spring.boot.banking.constant;

import java.text.SimpleDateFormat;

public interface Constant {

	long SYSTEM_USER_ID = 10;
	long BANK_CODE = 8880101; 
	double AMOUNT_ZERO = 0;
	String ACCOUNT_NUMBER_FORMAT ="%06d";
	boolean ACTIVE = true; 
	boolean INACTIVE = false;
	
	SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
}
