package com.wal.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://thzm.com/wsdl")
public interface IUserService
{
	
	//查询角色的数据
    @WebMethod
	public String  queryRoleData();
		
	// 找出学生表各个职务的学生数量和职务名称
	@WebMethod
	public String  queryGroupByRoleCount();
		
	//-- **学生所学课程的数量
	@WebMethod
	public String  queryStuAndkmCount(String name);
	
	//检查登录  2018.12.24新增
	@WebMethod
	public String   checkUserLogin(String  username,String  pwd);

	//数据菜单
	@WebMethod
	public String querGirdMenuData();
	
	@WebMethod
	public String querySexCount();
	
	@WebMethod
	public String queryClassCount();
	
	@WebMethod
	public String queryAgeCount();
	
	@WebMethod
	public String queryCityCount();
	
	@WebMethod
	public String queryProvinceCount();
	
	@WebMethod
	public String queryPhoneCount();
	
	@WebMethod
	public String queryPsystemCount();
	
	@WebMethod
	public String queryNameCount();
	
	@WebMethod
	public String queryMonthCount();
	


}
