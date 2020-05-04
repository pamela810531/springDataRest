package com.springDataRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springDataRest.db2.repositories.EmployeeRepository;
import com.springDataRest.dtos.EmployeeDTO;

@RestController
public class StoredProcedureInvokeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/getEmpQtyByEmployYn/{employYn}")
	public Integer getEmpQtyByEmployYn(@PathVariable("employYn") String employYn) {
		return employeeRepository.getEmpQtyByEmployYn(employYn);
	}

	@PostMapping("/updateAndGetEmpoyees")
	public List<EmployeeDTO> updateAndGetEmpoyees(@RequestParam("eId") String eId,
			@RequestParam("eYn") String targetEmpoyYn) {
		Long targetEmployeeId = Long.parseLong(eId);
		return employeeRepository.updateAndGetEmpoyees(targetEmployeeId, targetEmpoyYn);
	}

}
