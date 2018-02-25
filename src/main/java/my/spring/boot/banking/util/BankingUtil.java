package my.spring.boot.banking.util;

import my.spring.boot.banking.constant.Constant;

public class BankingUtil {
	public static String getAccountNumber(Long userId) {
		StringBuilder accountNumberSB = new StringBuilder();
		accountNumberSB.append(Constant.BANK_CODE);
		accountNumberSB.append(String.format(Constant.ACCOUNT_NUMBER_FORMAT,userId));
		return accountNumberSB.toString();
	}

}
