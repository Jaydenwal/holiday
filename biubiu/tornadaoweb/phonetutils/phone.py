import   re

def  checkPCorMobile(info):
     phoneheads=["android","mac","windowphone"];

     for  data  in  phoneheads:
         #print(data)
         #info头里面包含data
         val=re.search(data,info.lower())
        # print(val)
         if  val is None:
             return "PC"
         else:
             return "Mobile"


#aa=checkPCorMobile("Mozilla/5.0 (Linux; Android 8.0.0; DUK-AL20 Build/HUAWEIDUK-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.91 Mobile Safari/537.36")
#aa=checkPCorMobile("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
#print(aa)