package com.example.demo.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private EmployeeService empService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.empService = employeeService;
	}
	
	//build create employee REST API
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(empService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	//build get all employees REST API
	@GetMapping
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	//build get one employee by ID REST API
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
		return new ResponseEntity<Employee>(empService.findById(id), HttpStatus.OK);
	}
	
	//build put by ID REST API
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployeeInfoById(@PathVariable("id") long id, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(empService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	//build delete by id and firstName REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> updateEmployeeInfoById(@PathVariable("id") long id, @RequestParam String firstName){
		return empService.deleteEmployee(id, firstName);
	}
	
}
