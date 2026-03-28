package com.cg.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateLoanApplicationException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateLoanApplicationException(DuplicateLoanApplicationException e) {

		ErrorResponse error = new ErrorResponse(e.getClass().getSimpleName(), e.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidLoanAmountException.class)
	public ResponseEntity<ErrorResponse> handleInvalidLoanAmountException(InvalidLoanAmountException e) {

		ErrorResponse error = new ErrorResponse(e.getClass().getSimpleName(), e.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleLoanNotFoundException(LoanNotFoundException e) {

		ErrorResponse error = new ErrorResponse(e.getClass().getSimpleName(), e.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException e) {

		Map<String, String> fieldErrors = new HashMap<>();

		for (FieldError fe : e.getBindingResult().getFieldErrors()) {
			fieldErrors.put(fe.getField(), fe.getDefaultMessage());
		}

		Map<String, Object> response = new HashMap<>();
		response.put("error", "ValidationException");
		response.put("message", "Validation Failed");
		response.put("timestamp", LocalDateTime.now());
		response.put("errors", fieldErrors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}