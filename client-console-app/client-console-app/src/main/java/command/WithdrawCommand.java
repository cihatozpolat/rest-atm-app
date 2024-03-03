package command;

import java.io.IOException;

import account.UserAccount;
import service.UserAccountService;

public class WithdrawCommand implements Command{

	private final UserAccountService accountService;
	private final UserAccount account;
	private final double amount;
	
	public WithdrawCommand(UserAccountService accountService, UserAccount account, double amount) {
		this.accountService = accountService;
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void execute() throws NumberFormatException, IOException {
		if (accountService.withdraw(account.getCardNumber(), amount)) {
			account.setBalance(accountService.getBalance(account.getCardNumber()));
			System.out.println("\nWithdraw completed succesfully, new balance is $" + account.getBalance());
		}else {
			throw new IOException("Failed to withdraw from account.");
		}
	}

}
