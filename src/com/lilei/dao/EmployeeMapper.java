package com.lilei.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.lilei.bean.Employee;

public interface EmployeeMapper {
	public Employee getEmpById(Integer id);
//	public Employee getEmpByIdAndLastName(Integer id, String lastName);
	public Employee getEmpByIdAndLastName(@Param("tableName")String tableName,@Param("id")Integer id, @Param("lastName")String lastName);
	public Long addEmp(Employee employee);
	public void updateEmp(Employee employee);
	public void deleteEmpById(Integer id);
	public List<Employee> getEmpListByNameLike(@Param("name")String name);
	public Employee getEmpByMap(Map<String,Object> map);
	public Map<String,Object> getEmpByIdReturnMap(Integer id);
	@MapKey("id")
	public Map<Integer,Employee> getEmpListByNameLikeReturnEmployeeMap(String name);
	public List<Employee> getEmpByDeptId(Integer deptId);
}
