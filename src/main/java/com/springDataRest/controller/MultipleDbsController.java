package com.springDataRest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springDataRest.customizedRepositories.CustomizedDB2Repository;
import com.springDataRest.db2.repositories.Db2DummyRepository;
import com.springDataRest.msSql.repositories.MsSqlDummyRepository;

@RestController
public class MultipleDbsController {

	@Autowired
	private CustomizedDB2Repository customizedDB2Repository;

	@Autowired
	private Db2DummyRepository db2DummyRepository;

	@Autowired
	private MsSqlDummyRepository msSqlDummyRepository;

	/*
	 * 用entity manager執行Native Query， 會回傳單純的無欄位名的Result List
	 */
	@GetMapping("/db2/NativeQuery/byEntityManager")
	public List db2EmNativeQuery() {
		return customizedDB2Repository.getAllCompanies();
	}
	
	@GetMapping("/msSql/NativeQuery/byEntityManager")
	public List msSqlemNativeQuery() {
		return customizedDB2Repository.getAllShops();
	}

	@GetMapping("/db2/NativeQuery/byDummyEntity")
	public List<Map<String, Object>> db2DummyEntityNativeQuery() {
		return db2DummyRepository.getAllChiefs();
	}

	@GetMapping("/mssql/NativeQuery/byDummyEntity")
	public List<Map<String, Object>> msSqlDummyEntityNativeQuery() {
		return msSqlDummyRepository.getAllCrews();
	}

}
