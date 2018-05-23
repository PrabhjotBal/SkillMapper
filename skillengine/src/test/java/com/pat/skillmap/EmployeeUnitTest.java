package com.pat.skillmap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pat.skillmap.dao.EmployeeDAO;
import com.pat.skillmap.model.Employee;

public class EmployeeUnitTest {

	private AnnotationConfigApplicationContext context;
	private Employee employee;
	private EmployeeDAO employeeDAO;
	
	@Before
	public void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.pat.skillmap");
		context.refresh();
		employee=(Employee)context.getBean("employee");
		employeeDAO=(EmployeeDAO)context.getBean("employeeDAO");
	}
	@Test
	public void test_employee_save_success()
	{
		employee.setEmail("prabh@gmail.com");
		employee.setPassword("1234");
		employee.setEmployeeName("Prabhjot");
		employee.setGender("male");
		
		assertEquals(true, employeeDAO.addEmployee(employee));
	}
}
