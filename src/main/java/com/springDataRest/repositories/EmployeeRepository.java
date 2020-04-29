package com.springDataRest.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.springDataRest.entities.Department;
import com.springDataRest.entities.Employee;

@RepositoryRestResource
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@RestResource
	void deleteByEmployeeName(String employeeName);

	@Procedure(name = "getEmpQtyByEmployYn")
	Integer getEmpQtyByEmployYn(@Param("TARGET_EMPLOY_YN") String employYn);

	List<Employee> findByDepartmentAndEmployYn(Department department, String employYn);
}
