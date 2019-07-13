package com.lilei.dao;

import java.util.List;

import com.lilei.bean.Employee;

public interface EmployeeDynamicSqlMapper {
	public List<Employee> getEmpByConditionIf(Employee employee);
	public List<Employee> getEmpByConditionTrim(Employee employee);
}
