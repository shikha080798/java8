package com.hcl.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findEmployeeBySalaryGreaterThan(double salary);
	
	public List<Employee> findEmployeeBySalary(double salary);

}
