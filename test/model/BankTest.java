package model;

import java.util.Arrays;

public class BankTest {

	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.loadUsers();
		System.out.println(Arrays.toString(bank.getDatabase().toArray()));
	}

}
