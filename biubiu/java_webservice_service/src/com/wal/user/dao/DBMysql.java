package com.wal.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wal.file.FilePropertiesUtils;
import com.wal.model.Menu;
import com.wal.model.Person;
import com.wal.model.Role;
import com.wal.model.StuAndRole;

public class DBMysql implements DB
{
	static String imgpath="";
	static
	{
		imgpath=FilePropertiesUtils.getImageUtilPath();
	}
	private Connection conn;
	public DBMysql()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thzmdb","root","123456");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List queryRoleData()
	{
		
		String sql = "select * from t_role";
		List<Role> list = new ArrayList<Role>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Role role = new Role();
				role.setRid(rs.getInt(1));
				role.setRname(rs.getString(2));
				list.add(role);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public List queryRoleGroupCount()
	{
		String sql="SELECT  rname,COUNT(sjob)    FROM  t_stus  RIGHT  JOIN  t_role ON sjob=rid  GROUP BY  rname";
		
		List<StuAndRole> list = new ArrayList<StuAndRole>();
		
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				StuAndRole crole = new StuAndRole();
				crole.setRname(rs.getString(1));
				crole.setRcount(rs.getInt(2));
				
				list.add(crole);
			}
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		
		finally
		{
			if(null!=conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public  String   queryStuAndkmCount(String  stuName)
	{
       String sql="SELECT COUNT(jid),sname  FROM (SELECT   * FROM  t_stus  WHERE  sname=?) tmp INNER  JOIN  " +
    		" t_score  ON tmp.sid=t_score.sid  GROUP  BY sname";
		
       String data="";
		
		try {
			PreparedStatement  pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,stuName);
			ResultSet  rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				data=rs.getInt(1)+","+rs.getString(2);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return data;
	}
	
	public int checkUserLogin(String name,String pwd)
	{
		String sql = "SELECT  COUNT(*) FROM  t_stus  WHERE sname=? AND  spwd=?";
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
	
	public List<Menu> queryMenuData()
	{
		String sql = "SELECT  * FROM  t_menu";
		
		List<Menu> listMenu = new ArrayList<Menu>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Menu menu = new Menu();
				menu.setTid(rs.getInt(1));
				menu.setTname(rs.getString(2));
				menu.setTurl(rs.getString(3));
				menu.setImgpath(imgpath+rs.getString(5));
				
				listMenu.add(menu);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listMenu;
	}
	
	
	public List<Person> querySexCount()
	{
		System.out.println("DBMysql  is   querySexCount");
		String sql="SELECT psex,COUNT(*) AS '数量' FROM t_person GROUP BY psex";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setPsex(rs.getString(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public List<Person> queryClassCount()
	{
		System.out.println("DBMysql  is   queryClassCount");
		String sql="SELECT pclass,COUNT(*) AS '数量' FROM t_person GROUP BY pclass";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setPclass(rs.getString(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public List<Person> queryAgeCount()
	{
		System.out.println("DBMysql  is   queryAgeCount");
		String sql = "SELECT (SUBSTR(NOW(),1,4)-SUBSTR(pbirthday,1,4)) AS 'age',COUNT(*)AS '数量'\r\n" + 
				" FROM t_person GROUP BY SUBSTR(NOW(),1,4)-SUBSTR(pbirthday,1,4)";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setAge(rs.getString(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<Person> queryCityCount()
	{
		System.out.println("DBMysql  is   queryCityCount");
		String sql = "SELECT SUBSTR(paddress,3,3)AS 'city',\r\n" + 
				"FORMAT(COUNT(*)/(SELECT COUNT(*) FROM t_person WHERE paddress LIKE '江苏%'),2) AS '市级比例'\r\n" + 
				"FROM t_person WHERE t_person.`paddress` LIKE '江苏%' GROUP BY SUBSTR(paddress,3,3);";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setCity(rs.getString(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<Person> queryProvinceCount()
	{
		System.out.println("DBMysql  is   queryProvinceCount");
		String sql = "SELECT SUBSTR(paddress,1,2)AS'省份名称',COUNT(*)AS'数量' FROM t_person GROUP BY SUBSTR(paddress,1,2)";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setProvince(rs.getString(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<Person> queryPhoneCount()
	{
		System.out.println("DBMysql  is   queryPhoneCount");
		String sql = "SELECT phone,COUNT(*) FROM t_person GROUP BY phone";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setPhone(rs.getString(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<Person> queryPsystemCount()
	{
		System.out.println("DBMysql  is   queryPsystemCount");
		String sql = "SELECT psystem,COUNT(*) FROM t_person GROUP BY psystem";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setPsystem(rs.getString(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<Person> queryNameCount()
	{
		System.out.println("DBMysql  is   queryNameCount");
		String sql = "SELECT  COUNT(*), SUBSTR(pname,1,1) AS'name' FROM  t_person  GROUP  BY  SUBSTR(pname,1,1)";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setName(rs.getString(2));
				person.setCount(rs.getDouble(1));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public List<Person> queryMonthCount()
	{
		System.out.println("DBMysql  is   queryMonthCount");
		String sql = "SELECT SUBSTR(pbirthday,6,2) AS 'month',COUNT(*)AS '数量' FROM t_person GROUP BY SUBSTR(pbirthday,6,2)";
		
		List<Person> list = new ArrayList<Person>();
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Person person = new Person();
				person.setMonth(rs.getDouble(1));
				person.setCount(rs.getDouble(2));
				list.add(person);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=conn)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}



}
