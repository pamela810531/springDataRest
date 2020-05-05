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
//	@PersistenceContext
	private EntityManager db2EntityManager;
	// auto-wiring the factory...
//	@Autowired 
//	private EntityManagerFactory db2EntityManager;

	@Autowired 
	@Qualifier("msSqlEntityManager")
//	@PersistenceContext(name="msSqlEntityManager")
	private EntityManager msEntityManager;

	// 回傳資料並無包含欄位名字
	public List getAllCompanies() {
		return db2EntityManager.createNativeQuery("SELECT COMPANY_ID AS A, COMPANY_NAME AS B FROM COMPANY_PAM")
				.getResultList();
		// but it creates the manager every time.
//		return db2EntityManager.createEntityManager().createNativeQuery("SELECT COMPANY_ID AS A, COMPANY_NAME AS B FROM COMPANY_PAM")
//				.getResultList();
	}

	// 回傳資料並無包含欄位名字
	public List getAllShops() {
		return msEntityManager.createNativeQuery("SELECT * FROM SHOP_PAM")
				.getResultList();
	}
	
}
