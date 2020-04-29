package com.springDataRest.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class ApiParametersCheckUtil {
	@Autowired
	private ErrorMessagesUtil errorMessagesUtil;

	public Map<String, List<String>> checkParameters(JsonNode queryConditionsJson) {
		/*
		 * 所有的檢核結果。 Key=錯誤代碼; Value=錯誤訊息。
		 */
		Map<String, List<String>> checkResult = new TreeMap<String, List<String>>();
		List<String> paramMissingList = new ArrayList<String>();

		int employYnLengthLimited = 1;

		// 檢核departmentId 型態是否正確
		if (queryConditionsJson.has("departmentId")) {
			try {
				Long.parseLong(queryConditionsJson.get("departmentId").asText());
			} catch (NumberFormatException e) {
				checkResult.put("0403", new ArrayList<String>(Arrays.asList(errorMessagesUtil.getErrMap().get("0403")
						.replace("#paramName", "departmentId").replace("#type", "Long"))));
			}
		} else {
			paramMissingList.add("departmentId");
		}

		// 檢核employYn是否為有效的數值
		if (queryConditionsJson.has("employYn")) {
			String employYn = queryConditionsJson.get("employYn").asText().trim();
			if (employYn.length() != employYnLengthLimited)
				checkResult.put("0402",
						new ArrayList<String>(Arrays
								.asList(errorMessagesUtil.getErrMap().get("0402").replace("#paramName", "employYn")
										.replace("#limitLength", Integer.toString(employYnLengthLimited)))));
		} else {
			paramMissingList.add("employYn");
		}

		if (!paramMissingList.isEmpty()) {
			checkResult.put("0401", new ArrayList<String>(Arrays.asList(
					errorMessagesUtil.getErrMap().get("0401").replace("#paramsName", paramMissingList.toString()))));
		}

		return checkResult;
	}

}
