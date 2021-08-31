package com.hcl.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.employee.dto.EmployeeDto;
import com.hcl.employee.dto.EmployeeRequestDto;
import com.hcl.employee.dto.EmployeeResponseDto;
import com.hcl.employee.exception.EmployeeException;
import com.hcl.employee.service.IEmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	IEmployeeService iEmployeeService;
	
	@PostMapping("/employees")
	public ResponseEntity<String> saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) throws EmployeeException{
		return new ResponseEntity<String>(iEmployeeService.saveEmployee(employeeRequestDto),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/employees/higherSalary")
	public ResponseEntity<List<EmployeeResponseDto>> havingGreaterSalary(@RequestParam double salary) throws EmployeeException{
		return new ResponseEntity<List<EmployeeResponseDto>>(iEmployeeService.havingGreaterSalary(salary),HttpStatus.OK);
	}
	
	@GetMapping("/employees/lowerSalary")
	public ResponseEntity<List<EmployeeDto>> havingLessSalary(@RequestParam double salary) throws EmployeeException{
		return new ResponseEntity<List<EmployeeDto>>(iEmployeeService.havingLessSalary(salary),HttpStatus.OK);
	}
	
	@GetMapping("/employees/hike")
	public ResponseEntity<Map<String,Double>> hikeForThoseHavingLessSalary(@RequestParam double salary,@RequestParam double providedHike)
			throws EmployeeException{
		return new ResponseEntity<Map<String,Double>>(iEmployeeService.hikeThoseHavinglessSalary(salary, providedHike),HttpStatus.OK);
	}

}
