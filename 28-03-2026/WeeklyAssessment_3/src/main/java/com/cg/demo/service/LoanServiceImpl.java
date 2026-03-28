package com.cg.demo.service;

import java.util.List;

import com.cg.demo.entity.Loan;

public interface LoanServiceImpl {
	public void addLoan(Loan l);

	public List<Loan> fetchAll();

	public List<Loan> fetchById(int id);

	public Loan updateLoanStatus(int id, String status);

}
