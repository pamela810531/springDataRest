package com.springDataRest.msSql.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springDataRest.dummyEntity.DummyEntity;

@Repository
public interface MsSqlDummyRepository extends JpaRepository<DummyEntity, Integer> {

	@Query(value = "SELECT CREW_ID, CREW_NAME FROM CREW_PAM", nativeQuery = true)
	List<Map<String, Object>> getAllCrews();
}
