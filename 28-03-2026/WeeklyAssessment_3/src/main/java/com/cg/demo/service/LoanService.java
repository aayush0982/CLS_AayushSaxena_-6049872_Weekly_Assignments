package com.cg.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.entity.Loan;
import com.cg.demo.exception.DuplicateLoanApplicationException;
import com.cg.demo.exception.InvalidLoanAmountException;
import com.cg.demo.exception.LoanNotFoundException;
import com.cg.demo.repository.LoanRepository;

@Service
public class LoanService implements LoanServiceImpl {

	@Autowired
	private LoanRepository loanRepo;

	public void addLoan(Loan l) {

		System.out.println("ID: " + l.getId());

		final double MIN_LOAN_AMOUNT = 1;
		final double MAX_LOAN_AMOUNT = 5000000;

		if (l.getLoanAmount() < MIN_LOAN_AMOUNT || l.getLoanAmount() > MAX_LOAN_AMOUNT) {
			throw new InvalidLoanAmountException(
					"Loan amount must be between " + MIN_LOAN_AMOUNT + " and " + MAX_LOAN_AMOUNT);
		}

		List<Loan> existingLoans = loanRepo.findByApplicantName(l.getApplicantName());

		for (Loan loan : existingLoans) {
			if ("PENDING".equalsIgnoreCase(loan.getStatus())) {
				throw new DuplicateLoanApplicationException(
						"Loan already in PENDING state for applicant: " + l.getApplicantName());
			}
		}

		l.setId(null);

		loanRepo.save(l);
		System.out.println("Loan Added Successfully");
	}

	@Override
	public List<Loan> fetchAll() {
		return loanRepo.findAll();
	}

	@Override
	public List<Loan> fetchById(int id) {
		Loan loan = loanRepo.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

		return List.of(loan);
	}

	@Override
	public Loan updateLoanStatus(int id, String status) {

		Loan loan = loanRepo.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id));

		loan.setStatus(status);

		return loanRepo.save(loan);
	}

}