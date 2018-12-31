package com.wal.user.service.impl;

import java.util.List;

import javax.jws.WebService;

import com.wal.model.Menu;
import com.wal.model.Person;
import com.wal.model.Role;
import com.wal.model.StuAndRole;
import com.wal.user.dao.DBMysql;
import com.wal.user.service.interfaces.IUserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebService(portName="userservice",
serviceName="UserServiceImpl",
targetNamespace="http://thzm.com/wsdl",
endpointInterface="com.wal.user.service.interfaces.IUserService")
public class UserServiceImpl implements IUserService
{

	@Override
	public String queryRoleData()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryRoleData  start...  ");
		
		DBMysql db = new DBMysql();
		
		List<Role> list = db.queryRoleData();

		System.out.println("--->" + list.size());

		// webservice发布的数据应该是各个平台和语言统一能够解析的数据格式:
		// json [{},{},{}]
		
		JSONArray array = new JSONArray();
		for(Role role:list)
		{
			JSONObject obj = new JSONObject();
			obj.put("id", role.getRid());
			obj.put("rname", role.getRname());
			array.add(obj);
		}
		System.out.println("JSON-->"+array.toString());
		
		return array.toString();
	}
	
	@Override
	public String queryGroupByRoleCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGroupByRoleCount  start... ");
		
		DBMysql db = new DBMysql();
		List<StuAndRole> list = db.queryRoleGroupCount();
		
		System.out.println("--->"+list.size());
		JSONArray array = new JSONArray();
		for(StuAndRole crole:list)
		{
			JSONObject obj = new JSONObject();
			obj.put("rname", crole.getRname());
			obj.put("rcount", crole.getRcount());
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());

		return array.toString();
	}
	
	@Override
	public String queryStuAndkmCount(String name) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGroupByRoleCount  start...  ");
		
		DBMysql db = new DBMysql();
		
		String  data=db.queryStuAndkmCount(name);
		
		System.out.println("data-->"+data);
		return data;
	}

	@Override
	public String checkUserLogin(String username, String pwd)
	{
		// TODO Auto-generated method stub
		DBMysql db = new DBMysql();
		
		System.out.println("UserServiceImpl  is checkUserLogin  start...  ");
		
		int count = db.checkUserLogin(username, pwd);
		if(count>0)
		{
			return"登录成功";
		}
		return "登录失败";
	}

	@Override
	public String querGirdMenuData()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGirdMenuData  start...");
		DBMysql db = new DBMysql();
		List<Menu> list = db.queryMenuData();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
		
		System.out.println("strJson-->"+strJson);
		
		return strJson;
	}

	@Override
	public String querySexCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is querySexCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.querySexCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	}

	@Override
	public String queryClassCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryClassCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryClassCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
		
	}

	@Override
	public String queryAgeCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryAgeCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryAgeCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	
	}

	@Override
	public String queryCityCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryAgeCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryCityCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	}

	@Override
	public String queryProvinceCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryAgeCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryProvinceCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	}

	@Override
	public String queryPhoneCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryAgeCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryPhoneCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	}

	@Override
	public String queryPsystemCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryAgeCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryPsystemCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	}

	@Override
	public String queryNameCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryAgeCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryNameCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	}

	@Override
	public String queryMonthCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryAgeCount  start...");
		DBMysql db = new DBMysql();
		List<Person> list = db.queryMonthCount();
		
		//alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
				
		System.out.println("strJson-->"+strJson);
				
		return strJson;
	}
	

}
