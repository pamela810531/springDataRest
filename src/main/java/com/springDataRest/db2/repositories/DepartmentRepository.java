package com.springDataRest.db2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springDataRest.db2.entities.Department;

@RepositoryRestResource
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
