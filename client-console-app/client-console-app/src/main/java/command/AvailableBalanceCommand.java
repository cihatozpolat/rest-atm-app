package command;

import java.io.IOException;

import account.UserAccount;
import service.UserAccountService;

public class AvailableBalanceCommand implements Command {

	private final UserAccountService accountService;
	private final UserAccount account;
	
	public AvailableBalanceCommand(UserAccountService accountService, UserAccount account) {
		this.accountService = accountService;
		this.account = account;
	}
	
	@Override
	public void execute() throws NumberFormatException, IOException {
		double balance = accountService.getBalance(account.getCardNumber());
		if (balance >= 0L) {
			account.setBalance(balance);
			System.out.println("\n Available balance is $" + account.getBalance());
		} else {
			throw new IOException("Failed to get balance of the account.");
		}
	}

}
