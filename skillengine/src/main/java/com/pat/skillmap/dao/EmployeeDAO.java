package com.pat.skillmap.dao;

import java.util.List;

import com.pat.skillmap.model.Employee;

public interface EmployeeDAO {

		boolean addEmployee(Employee employee);
		boolean updateEmployee(Employee employee);
		boolean deleteEmployee(String empId);
		Employee getEmployeeById(String emailId);
		List<Employee> getAllEmployee();
		public boolean validateEmployee(String emailId,String password);
	}


