package com.cg.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.entity.Employee;
import com.cg.demo.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = employeeRepo.findAll();
		return empList;
	}

	@Override
	public Employee getEmployeeById(Integer empId) {
		Employee emp = employeeRepo.findById(empId).orElse(null);
		return emp;
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepo.save(employee);

	}

	@Override
	public void deleteEmployee(Integer empId) {
		employeeRepo.deleteById(empId);

	}

	@Override
	public void editEmployee(Employee employee) {
		Employee existingEmp = employeeRepo.findById(employee.getEmpId()).orElse(null);
		if (existingEmp != null) {
			employeeRepo.delete(existingEmp);
			employeeRepo.save(employee);
		}

	}

}
