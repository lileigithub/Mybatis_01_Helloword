package com.lilei.dao;

import com.lilei.bean.Department;
import com.lilei.bean.Employee;

public interface EmployeeResultMapMapper {
	public Employee getEmpAndDepById(Integer id);
	public Employee getEmpByIdStep(Integer id);
	public Department getDeptAndEmpById(Integer id);
	public Department getDeptAndEmpByIdStep(Integer id);
	public Employee getEmpByIddiscriminator(Integer id);
}
