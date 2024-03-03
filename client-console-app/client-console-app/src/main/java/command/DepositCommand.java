package command;

import java.io.IOException;

import account.UserAccount;
import service.UserAccountService;

public class DepositCommand implements Command{

	private final UserAccountService accountService;
	private final UserAccount account;
	private final double amount;
	
	public DepositCommand(UserAccountService accountService, UserAccount account, double amount) {
		this.accountService = accountService;
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void execute() throws NumberFormatException, IOException {
		if (accountService.deposit(account.getCardNumber(), amount)) {
			account.setBalance(accountService.getBalance(account.getCardNumber()));
			System.out.println("\nDeposit completed succesfully, new balance is $" + account.getBalance());
		}else {
			throw new IOException("Failed to deposit from account.");
		}
	}

}
