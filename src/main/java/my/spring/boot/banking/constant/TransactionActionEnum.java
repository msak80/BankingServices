package my.spring.boot.banking.constant;



public enum TransactionActionEnum {
	DISPUTE(Integer.valueOf(1).shortValue()),
	WITHDRAW(Integer.valueOf(2).shortValue());
	
	private short value;
	
	private TransactionActionEnum(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
