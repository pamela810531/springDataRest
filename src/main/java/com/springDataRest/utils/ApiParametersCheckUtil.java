package com.springDataRest.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class ApiParametersCheckUtil {
	@Autowired
	private ErrorMessagesUtil errorMessagesUtil;

	/**
	 * 參數檢核<br>
	 * 檢核項目：
	 * <li>檢核部門編號(departmentId)</li>
	 * <li>雇用狀態(EmployYn)</li>
	 * 
	 * @param queryConditionsJson
	 * @return
	 */
	public Map<String, List<String>> checkParameters(JsonNode queryConditionsJson) {
		/*
		 * 所有的檢核結果。 Key=錯誤代碼; Value=錯誤訊息。
		 */
		Map<String, List<String>> checkResult = new TreeMap<String, List<String>>();
		List<String> paramMissingList = new ArrayList<String>();

		// 檢核部門編號(departmentId)
		if (queryConditionsJson.has("departmentId")) {
			checkResult = checkDepartmentId(queryConditionsJson.get("departmentId").asText().trim(), checkResult);
		} else {
			paramMissingList.add("departmentId");
		}

		// 檢核雇用狀態(employYn)
		if (queryConditionsJson.has("employYn")) {
			checkResult = checkEmployYn(queryConditionsJson.get("employYn").asText().trim(), checkResult);
		} else {
			paramMissingList.add("employYn");
		}

		// 紀錄缺失的參數
		if (!paramMissingList.isEmpty()) {
			checkResult.put("0401", new ArrayList<String>(Arrays.asList(errorMessagesUtil.getErrMap().get("0401")
					.replace("#paramsName", paramMissingList.toString().replaceAll("[\\[\\]]", "")))));
		}

		return checkResult;
	}

	/**
	 * 部門編號(departmentId)參數檢核邏輯：
	 * <li>字元長度須為 1~10</li>
	 * <li>型態: Long</li> <br>
	 * 
	 * @param departmentId 部門編號
	 * @param checkResult  檢核結果
	 * @return
	 */
	private Map<String, List<String>> checkDepartmentId(String departmentId, Map<String, List<String>> checkResult) {
		int departmentIdLengthLimited = 10;
		if (departmentId.length() < 1 || departmentId.length() > departmentIdLengthLimited) {
			if (!checkResult.containsKey("0402")) {
				checkResult.put("0402", new ArrayList<String>());
			}
			checkResult.get("0402").add(errorMessagesUtil.getErrMap().get("0402").replace("#paramName", "departmentId")
					.replace("#limitLength", 1 + "~" + departmentIdLengthLimited));
		}

		try {
			Long.parseLong(departmentId);
		} catch (NumberFormatException e) {
			if (!checkResult.containsKey("0403")) {
				checkResult.put("0403", new ArrayList<String>());
			}
			checkResult.get("0403").add(errorMessagesUtil.getErrMap().get("0403").replace("#paramName", "departmentId")
					.replace("#type", "Long"));
		}
		return checkResult;
	}

	/**
	 * 雇用狀態(EmployYn)參數檢核邏輯：
	 * <li>長度須為1個字元</li>
	 * 
	 * @param employYn    雇用狀態
	 * @param checkResult 檢核結果
	 * @return
	 */
	private Map<String, List<String>> checkEmployYn(String employYn, Map<String, List<String>> checkResult) {
		int employYnLengthLimited = 1;
		if (employYn.length() != employYnLengthLimited) {
			if (!checkResult.containsKey("0402")) {
				checkResult.put("0402", new ArrayList<String>());
			}
			checkResult.get("0402").add(errorMessagesUtil.getErrMap().get("0402").replace("#paramName", "employYn")
					.replace("#limitLength", Integer.toString(employYnLengthLimited)));
		}
		return checkResult;
	}
	
	public String transferMapToMsg(Map<String, List<String>> errMsgMap) {
		String msg = "";
		for (String errCode : errMsgMap.keySet()) {
			msg += (errCode + ": " + errMsgMap.get(errCode).toString().replaceAll("[\\[\\]]", ""));
		}
		return msg;
	}
	
}
