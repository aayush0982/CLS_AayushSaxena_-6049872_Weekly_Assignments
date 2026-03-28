package com.cg.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String applicantName;
	@Column(nullable = true)
	private Double loanAmount;
	private String status;

	public Loan() {
	}

	public Loan(Integer id, String applicantName, Double loanAmount, String status) {
		this.applicantName = applicantName;
		this.loanAmount = loanAmount;
		this.status = status;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", applicantName=" + applicantName + ", loanAmount=" + loanAmount + ", status="
				+ status + "]";
	}
}
