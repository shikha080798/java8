package com.hcl.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.hcl.employee.dto.EmployeeDto;
import com.hcl.employee.dto.EmployeeRequestDto;
import com.hcl.employee.dto.EmployeeResponseDto;
import com.hcl.employee.exception.EmployeeException;
import com.hcl.employee.model.Employee;

public interface IEmployeeService {
	
	public String saveEmployee(EmployeeRequestDto employeeRequestDto) throws EmployeeException;
	
	public List<EmployeeResponseDto> havingGreaterSalary(double salary) throws EmployeeException;
	
	public List<EmployeeDto> havingLessSalary(double salary) throws EmployeeException;
	
	public Map<String,Double> hikeThoseHavinglessSalary(double salary,double providedHike) throws EmployeeException;
	
	
	//method to convert employee list to employeeResponseDto list
	public static List<EmployeeResponseDto> convertEmployeeListToEmployeeResponseDtoList(List<Employee> employeeList) {
		List<EmployeeResponseDto> employeeResponseDtoList=new ArrayList<EmployeeResponseDto>();
		employeeList.forEach(emp->{
			EmployeeResponseDto employeeResponseDto=new EmployeeResponseDto();
			BeanUtils.copyProperties(emp,employeeResponseDto);
			employeeResponseDtoList.add(employeeResponseDto);
		});
		
		return employeeResponseDtoList;
		
	}
	
	public static List<EmployeeDto> convertEmployeeListToEmployeeDtoList(List<Employee> employeeList) {
		List<EmployeeDto> employeeDtoList=new ArrayList<EmployeeDto>();
		employeeList.forEach(emp->{
			EmployeeDto employeeResponseDto=new EmployeeDto();
			BeanUtils.copyProperties(emp,employeeResponseDto);
			employeeDtoList.add(employeeResponseDto);
		});
		
		return employeeDtoList;
		
	}




}
