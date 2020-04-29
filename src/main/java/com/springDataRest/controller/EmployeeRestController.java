package com.springDataRest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.springDataRest.dtos.EmployeeDTO;
import com.springDataRest.service.EmployeeService;
import com.springDataRest.utils.ApiParametersCheckUtil;
import com.springDataRest.utils.ErrorMessagesUtil;

@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ErrorMessagesUtil errorMessagesUtil;
	@Autowired
	private ApiParametersCheckUtil apiParametersCheckUtil;

	@GetMapping("/getEmployees")
	public ResponseEntity<Map<String, Object>> getEmployeeDTOs() {
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		Map<String, Object> result = new HashMap<String, Object>();
		String uuid = UUID.randomUUID().toString();
		String returnCode = "0000";
		String returnMessage = "Success";
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			employeeDTOs = employeeService.getEmployeeDTOs();
		} catch (Exception e) {
			returnCode = "4002";
			returnMessage = "Failed on: " + e.getMessage();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		result.put("TransactionId", uuid);
		result.put("ReturnCode", returnCode);
		result.put("ReturnMessage", returnMessage);
		result.put("ReturnData", employeeDTOs);

		return new ResponseEntity<Map<String, Object>>(result, httpStatus);
	}

	@PostMapping("/getEmployeesByDepartmentIdAndEmployYn")
	public ResponseEntity<Map<String, Object>> getEmployeesByDepartmentAndEmployYn(
			@RequestBody JsonNode queryConditionsJson) {
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		Map<String, Object> result = new HashMap<String, Object>();
		String uuid = UUID.randomUUID().toString();
		String returnCode = "0000";
		String returnMessage = "Success";

		Map<String, List<String>> checkResult = apiParametersCheckUtil.checkParameters(queryConditionsJson);
		if (!checkResult.isEmpty()) {
			returnCode = checkResult.keySet().toString();
			returnMessage = checkResult.toString();
		} else {
			try {
				Long departmentId = Long.parseLong(queryConditionsJson.get("departmentId").asText());
				String employYn = queryConditionsJson.get("employYn").asText();
				employeeDTOs = employeeService.getEmployeeDTOsByDepartmentIdAndEmployYn(departmentId, employYn);
			} catch (Exception e) {
				returnCode = "0400";
				returnMessage = "Failed on: " + errorMessagesUtil.getErrMap().get("0400");
			}
		}

		result.put("TransactionId", uuid);
		result.put("ReturnCode", returnCode);
		result.put("ReturnMessage", returnMessage);
		result.put("ReturnData", employeeDTOs);

		return new ResponseEntity<Map<String, Object>>(result,
				"Success".equals(returnMessage) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
