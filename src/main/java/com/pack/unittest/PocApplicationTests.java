package com.pack.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.xmlunit.validation.ValidationResult;

import com.pack.controller.EmployeeController;
import com.pack.dto.EmployeeDto;
import com.pack.repository.EmployeeRepository;
import com.pack.serviceimpl.EmployeeServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PocApplicationTests {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeRepository empRepo;

	@Test
	public void test() {
		employeeServiceImpl.addNewEmployee(new EmployeeDto());
		employeeServiceImpl.updateEmployee(new EmployeeDto());
		employeeServiceImpl.getAllEmployee();
		employeeServiceImpl.searchByName(null);
		employeeServiceImpl.searchBypincode(null);
		employeeServiceImpl.searchBySurName(null);
		employeeServiceImpl.sort("values");

	}
}
