package com.springDataRest.db2.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springDataRest.db2.entities.Db2DummyEntity;

@Repository
public interface Db2DummyRepository extends JpaRepository<Db2DummyEntity, Integer> {

	@Query(value = "SELECT CHIEF_ID AS ID, CHIEF_NAME AS NAME FROM CHIEF_PAM", nativeQuery = true)
	List<Map<String, Object>> getAllChiefs();
	
}
