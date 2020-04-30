package com.springDataRest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.springDataRest.dtos.EmployeeDTO;

@Table(name = "employee_pam")
@Entity(name = "employee")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "updateAndGetEmpoyees", procedureName = "UPDATE_AND_GET_EMPOYEES", parameters = {
				@StoredProcedureParameter(name = "TARGET_EMPLOYEE_ID", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "TARGET_EMPOY_YN", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "EMPLOYEES_RESULT", type = EmployeeDTO.class, mode = ParameterMode.REF_CURSOR) }),
		@NamedStoredProcedureQuery(name = "getEmpQtyByEmployYn", procedureName = "GET_EMPLOYEES_QTY_BY_EMPLOY_YN", parameters = {
				@StoredProcedureParameter(name = "TARGET_EMPLOY_YN", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "UPDATED_ROW", type = Integer.class, mode = ParameterMode.OUT) }) })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private String employeeName;
	@ManyToOne
	@JoinColumn(name = "departmentId")
	private Department department;
	private String employYn;

	public Employee() {
		super();
	}

	public Employee(Long employeeId, String employeeName, Department department, String employYn) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.department = department;
		this.employYn = employYn;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmployYn() {
		return employYn;
	}

	public void setEmployYn(String employYn) {
		this.employYn = employYn;
	}

}
