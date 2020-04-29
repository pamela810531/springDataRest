package com.springDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springDataRest.repositories.EmployeeRepository;

@RestController
public class StoredProcedureInvokeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/getEmpQtyByEmployYn/{employYn}")
	public Integer getEmpQtyByEmployYn(@PathVariable("employYn") String employYn) {
		return employeeRepository.getEmpQtyByEmployYn(employYn);
	}

}
