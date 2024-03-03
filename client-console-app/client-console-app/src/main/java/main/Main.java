package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import account.UserAccount;
import command.*;
import service.UserAccountService;

public class Main {

	public static void main(String[] args) {
		try {
			ATM atm = new ATM();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			UserAccountService accountService = UserAccountService.getInstance();

			while (true) {
				System.out.print("Enter your card number: ");
				String cardNumber = reader.readLine();

				System.out.print("Enter your PIN: ");
				String pin = reader.readLine();

				UserAccount account = accountService.login(cardNumber, pin);

				if (account == null) {
					System.out.print("Login failed!\n");
					continue;
				}

				while (true) {
					int choice = atm.getMenuChoice();

					switch (choice) {
					case 1:
						atm.runCommand(new AvailableBalanceCommand(accountService, account));
						break;
					case 2:
						double depositAmount = atm.getAmountInput("deposit");
						atm.runCommand(new DepositCommand(accountService, account, depositAmount));
						break;
					case 3:
						double withdrawAmount = atm.getAmountInput("withdraw");
						atm.runCommand(new WithdrawCommand(accountService, account, withdrawAmount));
						break;
					case 4:
						String newPIN = atm.getPINInput();
						atm.runCommand(new ChangeCardPINCommand(accountService, account, newPIN));
						break;
					case 5:
						System.out.println("Thank you for using the ATM");
						System.exit(0);
					default:
						System.out.println("Invalid choice");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
