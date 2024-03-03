package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import command.Command;

public class ATM {
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void runCommand(Command command) throws NumberFormatException, IOException {
        command.execute();
    }

    public int getMenuChoice() throws IOException {
        System.out.println("\n\nWelcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Change Card PIN");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        return Integer.parseInt(reader.readLine());
    }

    public double getAmountInput(String action) throws IOException {
        System.out.print("Enter amount to " + action + ": $");
        return Double.parseDouble(reader.readLine());
    }
    
    public String getPINInput() throws IOException {
        System.out.print("Enter new PIN code: ");
        return reader.readLine();
    }

}
