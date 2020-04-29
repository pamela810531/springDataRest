package com.springDataRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springDataRest.entities.Department;

@RepositoryRestResource
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
