package com.springDataRest.dtos;

import com.springDataRest.db2.entities.Employee;

public class EmployeeDTO {
	private Long employeeId;
	private String employeeName;
	private Long departmentId;

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(Employee employee) {
		super();
		this.employeeId = employee.getEmployeeId();
		this.employeeName = employee.getEmployeeName();
		this.departmentId = employee.getDepartment()==null?null:employee.getDepartment().getDepartmentId();
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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
