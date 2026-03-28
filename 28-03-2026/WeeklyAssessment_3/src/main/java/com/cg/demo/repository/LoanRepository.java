package com.cg.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

	List<Loan> findByApplicantName(String applicantName);


}
