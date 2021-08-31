package com.hcl.employee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.employee.dto.EmployeeDto;
import com.hcl.employee.dto.EmployeeRequestDto;
import com.hcl.employee.dto.EmployeeResponseDto;
import com.hcl.employee.exception.EmployeeException;
import com.hcl.employee.model.Employee;
import com.hcl.employee.repository.EmployeeRepository;
import com.hcl.employee.service.IEmployeeService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Override
	
	public String saveEmployee(EmployeeRequestDto employeeRequestDto) throws EmployeeException {
		logger.info("saveEmployee(): called",employeeRequestDto.getEmployeeName());
		if(employeeRequestDto.getAge()<18) {
			logger.warn("Age should not be less than 18");
			throw new EmployeeException("Age should be greater than 18");
		}
		if(((""+employeeRequestDto.getPhoneNo()).length()!=10)) {
			logger.warn("Phone number length should be 10");
			throw new EmployeeException("Length of the phone number should be equal to 10");
		}
		
		Employee employee=new Employee();
		BeanUtils.copyProperties(employeeRequestDto,employee);
		employeeRepository.save(employee);
		logger.info("Data saved into employee table successfully");
		
		return "Employee added successfully";
	}

	//List of employess those who have greater salary
	@Override
	public List<EmployeeResponseDto> havingGreaterSalary(double salary) throws EmployeeException {
		logger.info("Finding the list of employees whose salary is greater than",salary);
		List<Employee> employeeList=(List<Employee>) (employeeRepository.findAll().stream().filter(employee->employee.getSalary()>salary)).
				collect(Collectors.toList());
		if(employeeList.isEmpty()) {
			logger.warn("The employee is empty");
			throw new EmployeeException("No employees having salary greater than"+salary);
		}
		return IEmployeeService.convertEmployeeListToEmployeeResponseDtoList(employeeList);
	}

	//list of employees those who have less salary
	@Override
	public List<EmployeeDto> havingLessSalary(double salary) throws EmployeeException {
		logger.info("Finding the list of employees whose salary is less than",salary);
		List<Employee> employeeList=(List<Employee>) (employeeRepository.findAll().stream().filter(employee->employee.getSalary()<salary).
				collect(Collectors.toList()));
		if(employeeList.isEmpty()) {
			logger.warn("The employee list is empty");
			throw new EmployeeException("No employees having salary less than"+salary);
		}
		return IEmployeeService.convertEmployeeListToEmployeeDtoList(employeeList);
	}
  
	//Providing hike based on the salary
	@Override
	public Map<String, Double> hikeThoseHavinglessSalary(double salary, double providedHike) throws EmployeeException {
		logger.info("Giving hike to the list of employees whose salary is less than",salary);
		List<Employee> employeeList=(List<Employee>) (employeeRepository.findAll().stream().filter(employee->employee.getSalary()<salary).
				collect(Collectors.toList()));
		if(employeeList.isEmpty()) {
			logger.warn("The employee list is empty");
			throw new EmployeeException("No employees having salary less than"+salary);
		}
		Map<String,Double> map=new HashMap<>();
		employeeList.forEach(emp->{
			emp.setSalary(emp.getSalary()+providedHike);
			map.put(emp.getEmployeeName(),emp.getSalary());
			logger.info("Updating the changes on salary column");
			employeeRepository.flush();
			logger.info("Changes done successfully");
		});
		return map;
	}
	

}
