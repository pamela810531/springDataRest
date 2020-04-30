package com.springDataRest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springDataRest.dtos.EmployeeDTO;
import com.springDataRest.entities.Department;
import com.springDataRest.entities.Employee;
import com.springDataRest.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Value("${testingConfig.isThrowingErr}")
	private boolean isThrowingErr;

	public List<EmployeeDTO> getEmployeeDTOs() throws Exception {
		if (isThrowingErr) throw new Exception("testing Err");
		return parseToEmployeeDTO(employeeRepository.findAll());
	}
	
	public List<EmployeeDTO> getEmployeeDTOsByDepartmentIdAndEmployYn(Long departmentId, String employYn) throws Exception {
		if (isThrowingErr) throw new Exception("testing Err");
		return parseToEmployeeDTO(employeeRepository.findByDepartmentAndEmployYn(new Department(departmentId), employYn));
	}

	private List<EmployeeDTO> parseToEmployeeDTO(List<Employee> emps) {
		List<EmployeeDTO> empDtos = new ArrayList<EmployeeDTO>();
		for (Employee employee : emps) {
			empDtos.add(new EmployeeDTO(employee));
		}
		return empDtos;
	}
	
	public List<EmployeeDTO> updateAndGetEmpoyees(Long targetEmployeeId, String targetEmpoyYn) {
		return employeeRepository.updateAndGetEmpoyees(targetEmployeeId, targetEmpoyYn);
	}

}
