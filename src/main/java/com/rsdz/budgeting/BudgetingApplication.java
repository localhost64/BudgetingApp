package com.rsdz.budgeting;

import com.rsdz.budgeting.dao.CCTransactionDao;
import com.rsdz.budgeting.entity.AmexTransaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BudgetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (CCTransactionDao ccTransactionDao) {
		return (runner) -> {
			//this.saveTransaction(ccTransactionDao);
			//this.deleteTransactions(ccTransactionDao);
		};
	}

	private void saveTransaction(CCTransactionDao ccTransactionDao) {
		System.out.println("Saving Transaction...");
		AmexTransaction amexTransaction = new AmexTransaction("$100.00", "Food", "2024-07-23");
		ccTransactionDao.save(amexTransaction);
		System.out.println("Transaction Saved.");
	}

	private void deleteTransactions(CCTransactionDao ccTransactionDao) {
		System.out.println("Deleting All Transactions...");
		ccTransactionDao.deleteAll();
		System.out.println("Deleted All Transactions");
	}
}