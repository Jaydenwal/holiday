import  tornado.web
import  tornado.ioloop
import suds
from suds import  client
from phonetutils.phone import *
import json
from  phonetutils.cacheutils import CacheService


class IndexHandler(tornado.web.RequestHandler):
    #get请求 self:当前对象
    def get(self):
        print("接受到用户请求")
        #写一个消息
        self.render("login.html",failmsg=None)



# class UserHandler(tornado.web.RequestHandler):
#     def get(self):
#         print("接受到用户的userindex请求")
#         #跳转视图
#         self.render("index.html")



class UserHandler(tornado.web.RequestHandler):
    def post(self):
        print("接受到用户的userindex请求")
        # 区分用户的请求的动作
        method=self.get_argument("action")
        print("method-->",method)
        if method=="login":
            # 请求的头的信息为: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36
            # 请求的头的信息为: Mozilla/5.0 (Linux; Android 8.0.0; DUK-AL20 Build/HUAWEIDUK-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.91 Mobile Safari/537.36
            # anroid  mac windowmobile
            username=self.get_argument("username")
            userpwd=self.get_argument("userpwd")
            print(username,userpwd)

            url="http://127.0.0.1:8185/userdataservice/user?wsdl"
            service=suds.client.Client(url)
            msg=service.service.checkUserLogin(username,userpwd)
            print("msg-->",msg)

            #怎么来区分是浏览器还是手机的请求
            #得到请求的头
            headsInfo=self.request.headers

            #print("headsInfo-->",headsInfo)
            hinfo=headsInfo["User-Agent"]
            print("请求的头的信息为:",hinfo)
            val=checkPCorMobile(hinfo)
            print("val",val)

            jsonDatas=""
            if msg=='登录成功':
                #菜单数据是不经常变化的，我们应从缓存中获取，不应每次从数据库中响应
                #减少对数据库服务器的负载。1.0
                '''url="http://127.0.0.1:8185/userdataservice/user?wsdl"
                service = suds.client.Client(url)
                data = service.service.queryGirdMenuData()
                print("data-->",data)
                print(type(data))
                jsonDatas = json.loads(data)
                print("jsonDatas-->",jsonDatas)'''

                #2.0  从缓存中获取策略  内部缓存
                cacheService=CacheService()
                jsonDatas=cacheService.getMenuData("tmenudata")


                if val=="PC":
                    self.render("index.html",contentdata=jsonDatas)
                else:
                    #json数据格式
                    self.finish({"msg":"success","contentdata":jsonDatas})
            else:
                if val=="PC":
                    self.render("login.html", failmsg=msg)
                else:
                    self.finish({"msg": "fail"})
        elif method=="register":
            self.render("register.html")




# class AdminHandler(tornado.web.RequestHandler):
#     def post(self):
#         print("接受到用户的queryall请求")
#         #怎么来区分是浏览器还是手机的请求
#         #得到请求的头
#         headsInfo=self.request.headers
#         #print("headsInfo-->",headsInfo)
#         hinfo=headsInfo["User-Agent"]
#         print("请求的头的信息为:",hinfo)
#         self.finish({"message":"完成post请求"})


#Antv报表区
class AntvHandler(tornado.web.RequestHandler):

    def post(self):
        print("antv业务报表功能")
        method=self.get_argument("datas")
        print("method-->", method)

        if method=="querysexcount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.querySexCount()
            print("data-->",data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->",jsonDatas)
            self.finish({"jsonDatas":jsonDatas})

        elif method=="queryclasscount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryClassCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        elif method=="queryagecount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryAgeCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        elif method == "querycitycount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryCityCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        elif method == "queryprovincecount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryProvinceCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        elif method == "queryphonecount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryPhoneCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        elif method == "querypsystemcount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryPsystemCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        elif method == "querynamecount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryNameCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})

        elif method == "querymonthcount":
            url = "http://127.0.0.1:8185/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryMonthCount()
            print("data-->", data)
            print(type(data))
            jsonDatas = json.loads(data)
            print("jsonDatas-->", jsonDatas)
            self.finish({"jsonDatas": jsonDatas})





#设置配置项
settings={
    "template_path":"templates",
    "static_path":"static",
    #"static_url_prefix":"static",
}



#创建一个应用对象，包含路由,请求的动作名对应着业务类
app = tornado.web.Application([(r'/',IndexHandler),
                               (r'/user',UserHandler),
                               (r'/antv',AntvHandler)
                            #  (r'/queryall',AdminHandler)
                               ],**settings)


if __name__=="__main__":
    #绑定一个监听端口，内网穿透保持一致
    app.listen(81)
    # 启动web程序，开始监听端口的连接
    tornado.ioloop.IOLoop.current().start()
