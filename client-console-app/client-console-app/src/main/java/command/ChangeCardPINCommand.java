package command;

import java.io.IOException;

import account.UserAccount;
import service.UserAccountService;

public class ChangeCardPINCommand implements Command {

	private final UserAccountService accountService;
	private final UserAccount account;
	private final String newPIN;
	
	public ChangeCardPINCommand(UserAccountService accountService, UserAccount account, String newPIN) {
		this.accountService = accountService;
		this.account= account;
		this.newPIN = newPIN;
	}

	@Override
	public void execute() throws NumberFormatException, IOException {
		if (accountService.changeCardPin(account.getCardNumber(), newPIN)) {
			account.setPinCode(newPIN);
			System.out.println("\nCard PIN is succesfully changed!");
		}else {
			throw new IOException("Failed to change card PIN.");
		}
	}

}
