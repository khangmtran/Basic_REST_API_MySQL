package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository empRepo;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.empRepo = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

	@Override
	public Employee findById(long id) {
		Optional<Employee> employee = empRepo.findById(id);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundException("Employee", "Id", id);
		} else
			return employee.get();
	}

	@Override
	public Employee findByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = empRepo.findByEmail(email);
		if (employee.isEmpty())
			throw new ResourceNotFoundException("Employee", "Email", email);
		else
			return employee.get();
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		Optional<Employee> existingEmployeeOptional = empRepo.findById(id);
		if (existingEmployeeOptional.isEmpty())
			throw new ResourceNotFoundException("Employee", "Id", id);
		else {
			Employee existingEmployee = existingEmployeeOptional.get();
			existingEmployee.setFirstName(employee.getFirstName());
			existingEmployee.setLastName(employee.getLastName());
			existingEmployee.setEmail(employee.getEmail());
			empRepo.save(existingEmployee);
			return existingEmployee;
		}
	}

	@Override
	public ResponseEntity<String> deleteEmployee(long id, String name) {
		// TODO Auto-generated method stub
		List<Optional<Employee>> empList = empRepo.findAllByFirstName(name);
		Employee employee = new Employee(0, "", "", "");
		for (Optional<Employee> emp : empList) {
			if (emp.get().getId() == id) {
				employee = emp.get();
				empRepo.delete(emp.get());
			}
		}
		if (employee.getId() == 0)
			throw new ResourceNotFoundException("Employee", "Id", id);
		else {
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		}

	}

}
