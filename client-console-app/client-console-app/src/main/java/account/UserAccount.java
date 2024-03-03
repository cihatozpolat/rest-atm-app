package account;

public class UserAccount {
	private double balance;
	private String cardNumber;
	private String pinCode;
	
	public UserAccount(double balance, String cardNumber, String pinCode) {
		this.balance = balance;
		this.cardNumber = cardNumber;
		this.pinCode = pinCode;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


}
