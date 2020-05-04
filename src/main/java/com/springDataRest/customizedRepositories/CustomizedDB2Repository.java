package com.springDataRest.customizedRepositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CustomizedDB2Repository {
	
	@Autowired 
	@Qualifier("db2EntityManager")
	private EntityManager entityManager;

	// 回傳資料並無包含欄位名字
	public List getAllCompanies() {
		return entityManager.createNativeQuery("SELECT COMPANY_ID AS A, COMPANY_NAME AS B FROM COMPANY_PAM").getResultList();
	}
	
}
