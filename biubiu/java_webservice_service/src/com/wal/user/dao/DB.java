package com.wal.user.dao;

import java.util.List;

import com.wal.model.Menu;

public interface DB
{
	
	public List queryRoleData();

	public List queryRoleGroupCount() ;

	public String queryStuAndkmCount(String stuName) ;
	
	public int checkUserLogin(String name, String pwd) ;

	public List<Menu> queryMenuData() ;
	


}
