package com.lilei.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lilei.bean.Department;
import com.lilei.bean.Employee;
import com.lilei.dao.EmployeeDynamicSqlMapper;
import com.lilei.dao.EmployeeMapper;
import com.lilei.dao.EmployeeResultMapMapper;

public class MybatisTest {

	/**
	 * 两个重要的配置文件：
	 * 全局配置文件mybatis-config.xml，包括数据库连接信息，事务管理器信息等
	 * sql映射文件：每一个sql语句的映射信息（至关重要）
	 * 
	 * 
	 */
	@Test
	public void test() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =
		new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();//非线程安全，多资源竞争会被关闭
		try {
		Employee selectOne = session.selectOne(
		"com.lilei.mybatis.EmployeeMapper.selectEmp", 1);
		System.out.println(selectOne);
		} finally {
		session.close();
		}
	}
	public SqlSession openSession() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =
		new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory.openSession();
	}
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Test
	public void Test01() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			Employee employee = mapper.getEmpById(1);
			System.out.println(mapper.getClass());
			System.out.println(employee);
		} finally{
			session.close();
		}
		
	}
	/**
	 * 增删改允许定义三个返回值：Integer、Long、Boolean 
	 */
	@Test
	public void Test02() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			Employee employee = new Employee(null,"heiheihei","youxiang","2");
			System.out.println(mapper.addEmp(employee));
			System.out.println(employee);
			
//			Employee employee = new Employee(1,"1hao","youxiang","1");
//			mapper.updateEmp(employee);
			
//			mapper.deleteEmpById(3);
			//手动提交数据
			session.commit();
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test03() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			Employee employee = mapper.getEmpByIdAndLastName("tbl_employee", 5, "heiheihei");
			System.out.println(mapper.getClass());
			System.out.println(employee);
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test04() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			List<Employee> employees = mapper.getEmpListByNameLike("hei");
			for (Employee employee2 : employees) {
				System.out.println(employee2);
			}
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test05() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			Map<String, Object> map = new HashMap<>();
			map.put("id", 5);
			map.put("lastName", "heiheihei");
			Employee employee = mapper.getEmpByMap(map);
			System.out.println(employee);
		} finally{
			session.close();
		}
	}

	@Test
	public void Test06() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			Map<String,Object> map = mapper.getEmpByIdReturnMap(1);
			System.out.println(map);
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test07() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			Map<Integer,Employee> maps = mapper.getEmpListByNameLikeReturnEmployeeMap("%hei%");
			System.out.println(maps);
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test08() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeResultMapMapper mapper = session.getMapper(EmployeeResultMapMapper.class);//代理对象
			Employee employee = mapper.getEmpAndDepById(1);
			System.out.println(employee);
			System.out.println(employee.getDept());
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test09() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeResultMapMapper mapper = session.getMapper(EmployeeResultMapMapper.class);//代理对象
			Employee employee = mapper.getEmpByIdStep(1);
			System.out.println(employee.getLastName());
			//System.out.println(employee.getDept());
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test10() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeResultMapMapper mapper = session.getMapper(EmployeeResultMapMapper.class);//代理对象
			Department department = mapper.getDeptAndEmpById(1);
			System.out.println(department);
			System.out.println(department.getEmps());
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test11() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeResultMapMapper mapper = session.getMapper(EmployeeResultMapMapper.class);//代理对象
			Department department = mapper.getDeptAndEmpByIdStep(1);
			System.out.println(department);
			System.out.println(department.getEmps());
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test12() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeResultMapMapper mapper = session.getMapper(EmployeeResultMapMapper.class);//代理对象
			Employee employee = mapper.getEmpByIddiscriminator(1);
			System.out.println(employee);
			System.out.println(employee.getDept());
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test13() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeDynamicSqlMapper mapper = session.getMapper(EmployeeDynamicSqlMapper.class);//代理对象
			List<Employee> employee = mapper.getEmpByConditionIf(new Employee(1, "", null, null));
			System.out.println(employee);
		}finally{
			session.close();
		}
	}
	
	/**
	 * 两级缓存：
	 * 一级缓存（本地缓存）sqlssesion级别的,是一直开启的，类似有一个sqlssion级别的map
	 * 		与数据库同一次会话期间查询到的数据会放在本地缓存中，以后如果要获取相同的数据
	 * 		直接从缓存中拿，没必要再去查数据库
	 * 	失效的情况：
	 * 		1.不同的sqlsession
	 * 		2.不同的查询条件
	 * 		3.期间执行了增删改操作
	 * 		4.手动清空一级缓存 session.clearCache();
	 * 二级缓存（全局缓存） 基于namespace
	 * 		工作机制：
	 * 			1.一个会话，查询一条数据后，会把数据放在一级缓存中
	 * 			2.如果会话关闭，一级缓存会保存在二级缓存中，新的会话查询信息，就可以参照二级缓存
	 * 			3.一个session中，查了两个bean，会放在对应的namespace缓存中（map）
	 * 		使用：
	 * 			1.全局配置开启 <setting name="cacheEnabled" value="true"/>
	 * 			2.去mapper.xml中配置缓存策略
	 * 			3.POJO需要实现反序列化接口Serializable（因为缓存策略readonly默认为false，需要反序列化）
	 * 		和缓存有关的设置：
	 * 			1.全局设置cacheEnabled=false 只关闭二级缓存
	 * 			2.mapper.xml useCache=true=false 只关闭二级缓存
	 * 			3.每个增删改标签中都有flushCache=true：增删改执行完后清除缓存。一级、二级缓存都会被清空。	
	 * 			4.localCacheScope:本地缓存作用域（SESSION|STATEMENT）
	 * 			5.session.clearCache();只是清除当前的一级缓存
	 * 			
	 * 
	 */		
	
	@Test
	public void Test14() throws IOException{
		SqlSession session = openSession();
		try {
			//获取接口的实现类对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
			Employee employee = mapper.getEmpById(2);
			System.out.println(employee);
			//mapper.updateEmp(new Employee(2, "2hao", "123", "1"));
			Employee employee2 = mapper.getEmpById(2);
			System.out.println(employee2);
			System.out.println(employee==employee2);
		} finally{
			session.close();
		}
	}
	
	@Test
	public void Test15() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =
		new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		SqlSession session2 = sqlSessionFactory.openSession();
		//获取接口的实现类对象
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);//代理对象
		Employee employee = mapper.getEmpById(2);
		System.out.println(employee);
		session.close();//只有会话关闭后，查询结果才会从一级缓存放入二级缓存

		EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);//代理对象
		Employee employee2 = mapper2.getEmpById(2);
		System.out.println(employee2);
		System.out.println(employee==employee2);
		
	}
}
