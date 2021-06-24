package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.Entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

	public List<EmployeeEntity> findByempNameContaining(String namesearch);
	public List<EmployeeEntity> findByempSurnameContaining(String surnamesearch);
	public List<EmployeeEntity> findByempPincodeContaining(String pincode);
	
	
 
}
