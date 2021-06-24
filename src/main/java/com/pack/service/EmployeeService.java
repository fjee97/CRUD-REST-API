package com.pack.service;

import java.util.List;

import com.pack.Entity.EmployeeEntity;
import com.pack.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto getEmployeeByid(Integer empId);

	List getAllEmployee();

	EmployeeDto addNewEmployee(EmployeeDto employee);

	void updateEmployee(EmployeeDto employeedto);

	void deleteEmployee(Integer empId);
	
	
	public List<EmployeeEntity> searchByName(String name);

}
