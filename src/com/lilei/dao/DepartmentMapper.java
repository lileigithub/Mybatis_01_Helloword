package com.lilei.dao;

import com.lilei.bean.Department;

public interface DepartmentMapper {

	public Department getDeptById(Integer id);
	public Department getDeptAndEmpById(Integer id);
}
