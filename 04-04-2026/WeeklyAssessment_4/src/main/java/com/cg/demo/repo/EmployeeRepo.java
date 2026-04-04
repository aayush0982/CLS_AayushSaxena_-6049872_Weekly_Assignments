package com.cg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
