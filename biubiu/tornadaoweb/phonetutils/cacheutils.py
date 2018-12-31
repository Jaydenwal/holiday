import suds
from suds import client
from phonetutils.phone import *
import json


class CacheService():

    #全局静态数据，没有static
    cachedata={}

    #__私有属性
    __number=10

    def __init__(self):
        print("---------产生一个对象就初始化一次---------")
        #定义一个缓存的属性{键：值}
        #self.cachedata={}

    def getMenuData(self,key):

        #判断这个key在不在   self.cachedata
        if key in self.cachedata:
            print("缓存中有数据******")
            return self.cachedata[key]
        else:
            print("缓存中没有数据-----")
            url="http://127.0.0.1:8185/userdataservice/user?wsdl"
            service1 = suds.client.Client(url)
            data = service1.service.querGirdMenuData()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.cachedata[key] = jsonDatas
            return jsonDatas