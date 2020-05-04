package com.springDataRest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springDataRest.customizedRepositories.CustomizedDB2Repository;
import com.springDataRest.db2.repositories.Db2DummyRepository;

@RestController
public class MultipleDbsController {

	@Autowired
	private CustomizedDB2Repository customizedDB2Repository;

	@Autowired
	private Db2DummyRepository db2DummyRepository;

	/*
	 * 用entity manager執行Native Query， 會回傳單純的無欄位名的Result List
	 */
	@GetMapping("/db2/NativeQuery/byEntityManager")
	public List emNativeQuery() {
		return customizedDB2Repository.getAllCompanies();
	}
	
	@GetMapping("/db2/NativeQuery/byDummyEntity")
	public List<Map<String, Object>> dummyEntityNativeQuery() {
		return db2DummyRepository.getAllChiefs();
	}

}
