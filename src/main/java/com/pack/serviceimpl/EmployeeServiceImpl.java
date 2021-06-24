package com.pack.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.Entity.EmployeeEntity;
import com.pack.dto.EmployeeDto;
import com.pack.exception.ResourceNotFoundException;
import com.pack.repository.EmployeeRepository;
import com.pack.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository emprepo;

	@Override
	public EmployeeDto getEmployeeByid(Integer empId) {

		EmployeeDto employeedto = null;
		if (empId != null && empId > 0) {
			EmployeeEntity empentity = emprepo.findById(empId)
					.orElseThrow(() -> new ResourceNotFoundException(" data not found"));
			employeedto = convertEntityToModel(empentity);
		} else {
			throw new ResourceNotFoundException("No Data Found");
		}

		return employeedto;

	}

	@Override
	public List getAllEmployee() {
		List<EmployeeEntity> empentity = emprepo.findAll();

		if (empentity != null && !empentity.isEmpty()) {
			return empentity.stream().map(EmployeeServiceImpl::convertEntityToModel).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public EmployeeDto addNewEmployee(EmployeeDto employeedto) {
		if (employeedto != null) {
			EmployeeEntity empEntity = convertModelToEntity(employeedto);
			emprepo.save(empEntity);
			// employeedto.setEmpId(empEntity.getEmpId());
		} else {
			throw new org.springframework.security.core.userdetails.UsernameNotFoundException("No Employee id found");
		}
		return employeedto;
	};

	@Override
	public void updateEmployee(EmployeeDto employeedto) {
		if (employeedto != null) {
			EmployeeEntity empEntity = convertModelToEntity(employeedto);
			emprepo.save(empEntity);
		} else {
			throw new ResourceNotFoundException("Unable to Update Data");
		}

	}

	@Override
	public void deleteEmployee(Integer empId) {

		if (empId != null && empId > 0) {
			EmployeeEntity empEntity = emprepo.findById(empId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee id Not found"));
			if (empEntity != null && empEntity.getEmpId() != null) {
				emprepo.delete(empEntity);
			}
		} else {
			throw new ResourceNotFoundException("Unable to Delete Data");
		}

	}

	private static EmployeeDto convertEntityToModel(EmployeeEntity empentity) {

		EmployeeDto empdto = new EmployeeDto();

		empdto.setEmpId(empentity.getEmpId());
		empdto.setEmpName(empentity.getEmpName());
		empdto.setEmpSurname(empentity.getEmpSurname());
		empdto.setEmpDob(empentity.getEmpDob());
		empdto.setEmpJod(empentity.getEmpJod());
		empdto.setEmpPincode(empentity.getEmpPincode());

		return empdto;
	}

	private EmployeeEntity convertModelToEntity(EmployeeDto employeedto) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setEmpId(employeedto.getEmpId());
		employeeEntity.setEmpName(employeedto.getEmpName());
		employeeEntity.setEmpSurname(employeedto.getEmpSurname());
		employeeEntity.setEmpDob(employeedto.getEmpDob());
		employeeEntity.setEmpJod(employeedto.getEmpJod());
		employeeEntity.setEmpPincode(employeedto.getEmpPincode());
		return employeeEntity;
	}

	public List<EmployeeEntity> searchByName(String name) {
		List<EmployeeEntity> findByempNameContaining = emprepo.findByempNameContaining(name);
		return findByempNameContaining;
	}

	public List<EmployeeEntity> searchBySurName(String surname) {
		List<EmployeeEntity> findByempSurNameContaining = emprepo.findByempSurnameContaining(surname);
		return findByempSurNameContaining;

	}

	public List<EmployeeEntity> searchBypincode(String pincode) {
		List<EmployeeEntity> findByempSurNameContaining = emprepo.findByempPincodeContaining(pincode);
		return findByempSurNameContaining;

	}


	public List deleteById(Integer id) {
		emprepo.deleteById(id);
		List<EmployeeEntity> empentity = emprepo.findAll();

		if (empentity != null && !empentity.isEmpty()) {
			return empentity.stream().map(EmployeeServiceImpl::convertEntityToModel).collect(Collectors.toList());
		}

		return new ArrayList<>();
	}
	
	
	public List<EmployeeDto> DeleteEmployee(String name, Integer id) {
		List<EmployeeDto> list = getAllEmployee();
		if (name.equals("soft")) {
			List<EmployeeDto> softDelete = list.stream().filter(x -> x.getEmpId() != id).collect(Collectors.toList());
			return softDelete;
		} else if (name.equals("hard")) {
			List<EmployeeDto> hardDelete = deleteById(id);
			return hardDelete;
		}
		return null;
	}
	
	
	public List<EmployeeDto> sort(String sort) 
	{
		List<EmployeeDto> list = getAllEmployee();
		if (sort.equals("dob")) {
			Collections.sort(list, (c1, c2) -> c1.getEmpDob().compareTo(c2.getEmpDob()));
		} else if (sort.equals("jod")) {
			Collections.sort(list, (c1, c2) -> c1.getEmpJod().compareTo(c2.getEmpJod()));
		}
		return list;
	}
	
}
