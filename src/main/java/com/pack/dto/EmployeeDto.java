package com.pack.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class EmployeeDto {
	private Integer empId;
	@Size(min = 05, max = 15, message = "Name must be between 05 to 15 characters")
	private String empName;
	@NotBlank(message = "Surname Cant be Empty")
	private String empSurname;
	private Date empDob;
	private Date empJod;
	@Size(min = 06, max = 06, message = "Pin_code should not be greater or less than 6 ")
	private String empPincode;

}
