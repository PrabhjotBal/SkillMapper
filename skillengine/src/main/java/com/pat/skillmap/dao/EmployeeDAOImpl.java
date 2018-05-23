package com.pat.skillmap.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pat.skillmap.model.Employee;

@Repository(value="employeeDAO")
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addEmployee(Employee employee) {
		try {
			sessionFactory.getCurrentSession().save(employee);
			return true;
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}
	
	public boolean updateEmployee(Employee employee) {
		try {
			sessionFactory.getCurrentSession().update(employee);
			return true;
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}	}

	public boolean deleteEmployee(String employeeEmail) {
		sessionFactory.getCurrentSession().delete(findByEmployeeId(employeeEmail));	
		return true;
	}

	public Employee findByEmployeeId(String employeeId) {
		
		return (Employee)sessionFactory.getCurrentSession()
				.createQuery("from Employee where employeeId="+employeeId)
				.uniqueResult();
	}

	public Employee getEmployeeById(String emailId) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().get(Employee.class,emailId);
		return null;
	}

	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Employee").list();
		
	}
	public boolean validateEmployee(String emailId,String password)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("from Employee where email='"+emailId+"'and password='"+password+"'");
		Employee employee=(Employee)query.uniqueResult();
		if(employee!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
