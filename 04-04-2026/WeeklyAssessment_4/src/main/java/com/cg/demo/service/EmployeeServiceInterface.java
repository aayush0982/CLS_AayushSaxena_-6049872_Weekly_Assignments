package com.cg.demo.service;

import java.util.List;

import com.cg.demo.entity.Employee;

public interface EmployeeServiceInterface {
	List<Employee> getAllEmployees();

	Employee getEmployeeById(Integer empId);

	void addEmployee(Employee employee);

	void deleteEmployee(Integer empId);
	
	void editEmployee(Employee employee);
}
