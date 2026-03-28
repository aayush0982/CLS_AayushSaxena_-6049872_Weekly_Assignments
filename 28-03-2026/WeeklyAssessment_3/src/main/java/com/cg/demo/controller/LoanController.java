package com.cg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.demo.entity.Loan;
import com.cg.demo.service.LoanService;

import jakarta.validation.Valid;

@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;

	@PostMapping("/loans")
	public ResponseEntity<Loan> createLoan(@Valid @RequestBody Loan loan) {
		loan.setStatus("PENDING");
		loanService.addLoan(loan);
		return ResponseEntity.ok(loan);
	}

	@GetMapping("/loans")
	public ResponseEntity<List<Loan>> getAllLoans() {
		return ResponseEntity.ok(loanService.fetchAll());
	}

	@GetMapping("/loans/{id}")
	public ResponseEntity<List<Loan>> getLoanById(@PathVariable int id) {
	    return ResponseEntity.ok(loanService.fetchById(id));
	}

	@PutMapping("/loans/{id}/status")
	public ResponseEntity<List<Loan>> updateLoanStatus(
	        @PathVariable int id,
	        @RequestParam String status) {

	    loanService.updateLoanStatus(id, status);
	    return ResponseEntity.ok(loanService.fetchById(id));
	}
}