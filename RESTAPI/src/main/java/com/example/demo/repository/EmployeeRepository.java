package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findById(long id);
	Optional<Employee> findByEmail(String email);
	Employee findExistingEmployeeById(long id);
	List<Optional<Employee>> findAllByFirstName(String firstName);
}
