package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee findById(long id);
	Employee findByEmail(String email);
	Employee updateEmployee(Employee employee, long id);
	ResponseEntity<String> deleteEmployee(long id, String firstName);
}
