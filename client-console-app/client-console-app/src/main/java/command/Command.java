package command;

import java.io.IOException;

import account.UserAccount;

public interface Command {
	void execute() throws NumberFormatException, IOException;
}
