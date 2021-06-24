package com.pack.controller;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.Entity.EmployeeEntity;
import com.pack.dto.EmployeeDto;
import com.pack.serviceimpl.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl service;

	@GetMapping("/{id}")
	public EmployeeDto getByEmployeeId(@PathVariable("id") Integer empId) {
		return service.getEmployeeByid(empId);

	}

	@GetMapping("/sorting")
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(@RequestParam("sort") String sort) {
		List<EmployeeDto> sortEmployee = service.sort(sort);
		return new ResponseEntity<List<EmployeeDto>>(sortEmployee, HttpStatus.OK);

	}

	@PostMapping("/posting")
	public ResponseEntity addEmployee(@Valid @RequestBody EmployeeDto empDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(result.getFieldErrors().iterator().next().getDefaultMessage());
		}
		EmployeeDto employeeDto = service.addNewEmployee(empDto);

		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {

		service.updateEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);

	}

	/* it will not in use because of hard delete 
	 * // http://localhost:9091/api/6
	 * 
	 * @DeleteMapping("{id}") public void deleteEmployee(@PathVariable Integer id) {
	 * service.deleteEmployee(id); }
	 */
	
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeEntity>> SearchEmployeeByName(@RequestParam("name") String searchdetails,@RequestParam("details") String nam) {
		//System.out.println(na);
		if(searchdetails.equals("SearchByname")) {
		List<EmployeeEntity> search= service.searchByName(nam);
		return new ResponseEntity<List<EmployeeEntity>>(search, HttpStatus.OK);
		}
		else if (searchdetails.equals("SearchBysurname")) {
			
			List<EmployeeEntity> search= service.searchBySurName(nam);
			return new ResponseEntity<List<EmployeeEntity>>(search, HttpStatus.OK);
		}
		
		else if (searchdetails.equals("SearchBypincode")) {

			List<EmployeeEntity> search= service.searchBypincode(nam); 
			return new ResponseEntity<List<EmployeeEntity>>(search, HttpStatus.OK);
		
		}
	
		return new ResponseEntity<List<EmployeeEntity>>(HttpStatus.NOT_FOUND);

	}  

	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<EmployeeDto>> getDeletedEmployee(@RequestParam("name") String name,
			@PathVariable Integer id) {
		List<EmployeeDto> emp= service.DeleteEmployee(name,id);
		return new ResponseEntity<List<EmployeeDto>>(emp, HttpStatus.OK);

	}
}
