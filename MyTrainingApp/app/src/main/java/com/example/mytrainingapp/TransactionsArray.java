package com.example.mytrainingapp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/03
 */
public class TransactionsArray {

	private static List<Transaction> transactions = new ArrayList<>();

	private TransactionsArray() {}

	public static void setTransactions(List<Transaction> transactions) {
		TransactionsArray.transactions = transactions;
	}

	public static List<Transaction> getTransactions() {
		return transactions;
	}

	public static boolean isListEmpty() {
		return transactions.isEmpty();
	}

	public static Transaction getTransactionByNum(int num) {
		return transactions.get(num);
	}
}
