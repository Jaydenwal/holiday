package com.wal.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://thzm.com/wsdl")
public interface IUserService
{
	
	//��ѯ��ɫ������
    @WebMethod
	public String  queryRoleData();
		
	// �ҳ�ѧ�������ְ���ѧ��������ְ������
	@WebMethod
	public String  queryGroupByRoleCount();
		
	//-- **ѧ����ѧ�γ̵�����
	@WebMethod
	public String  queryStuAndkmCount(String name);
	
	//����¼  2018.12.24����
	@WebMethod
	public String   checkUserLogin(String  username,String  pwd);

	//���ݲ˵�
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
