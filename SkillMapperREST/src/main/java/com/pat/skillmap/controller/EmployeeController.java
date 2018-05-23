package com.pat.skillmap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pat.skillmap.dao.EmployeeDAO;
import com.pat.skillmap.model.Employee;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;

	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployee() {

		List<Employee> employees = employeeDAO.getAllEmployee();
		if (employees != null) {
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No Employee Found", HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		employeeDAO.addEmployee(employee);
		return new ResponseEntity<String>("Employee Saved", HttpStatus.CREATED);
	}

	@PostMapping("/delete/{emailId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("emailId") String emailId) {
		employeeDAO.deleteEmployee(emailId);
		return new ResponseEntity<String>("Employee Deleted", HttpStatus.OK);
	}

	@GetMapping("/employee/{emailId}")
	public ResponseEntity<?> employeeById(@PathVariable("emailId") String emailId) {
		Employee employee = employeeDAO.getEmployeeById(emailId);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		employeeDAO.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginEmployee(@RequestBody Employee employee) {
		boolean status = employeeDAO.validateEmployee(employee.getEmail(), employee.getPassword());
		if (status) {
			return new ResponseEntity<String>("Logged in Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid Username/Password", HttpStatus.NOT_FOUND);
		}
	}

}
