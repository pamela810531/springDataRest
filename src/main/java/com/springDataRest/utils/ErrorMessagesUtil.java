package com.springDataRest.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api-err-msg")
public class ErrorMessagesUtil {
	private Map<String, String> errMap = new HashMap<String, String>();

	public Map<String, String> getErrMap() {
		return errMap;
	}

	public void setErrMap(Map<String, String> errMap) {
		this.errMap = errMap;
	}
	
}
