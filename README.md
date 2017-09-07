# spring-mvc-demo
maven, spring 4.3.9


2017090701
----------

- 将Chrome路径放到配置文件中

webdriver + jenkins执行用例报“unknown error: cannot find Chrome binary”

http://blog.csdn.net/dreamtl/article/details/53486563

	System.setProperty("webdriver.chrome.driver", "files/chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	options.setBinary("chrome.exe路径");
	ChromeDriver driver = new ChromeDriver(options);



2017090605
----------

- 将截图放到网站下面，邮件中用URL路径

dispatcher-servlet.xml

	<!-- Spring MVC不处理静态资源 -->
	<mvc:default-servlet-handler />


mail.properties

	#mail sender settings
	# for example: smtp.sina.cn
	mail.server=smtp.126.com
	mail.port=25
	#the sender mail
	mail.sender=xxxx@126.com
	#the sender nickname
	mail.nickname=acoderxxxxx
	#sender mail username
	mail.username=acoder
	#sender mail password
	mail.password=xxxx
	mail.mailto=xxxx@qq.com

2017090604
----------

- 增加Apache Commons-Email插件，用于发邮件
- 通过commons-email将Selenium生成的截屏嵌入电子邮件正文中

2017090603
----------

- 增加 Selenium 3.5
- 支持Chrome测试


谷歌驱动下载地址：

http://chromedriver.storage.googleapis.com/index.html

对应版本：

	v2.21  v46-50
	
	v2.22  v49-52
	
	v2.23  v51-53
	
	v2.24  v52-57
	
	v2.25  v53-55
	
	v2.26  v53-55
	
	v2.27  v54-56
	
	v2.28  v55-57
	
	v2.29  v56-58

解决【chrome正受到自动测试软件的控制】提示问题（为驱动添加配置参数）

https://stackoverflow.com/questions/43143014/chrome-is-being-controlled-by-automated-test-software/43145088


2017090602
----------


TestNG eclipse 插件：

http://dl.bintray.com/testng-team/testng-eclipse-release/zipped/


- 增加log4j
- 增加 TestNG的简单教程 http://blog.csdn.net/guoguo527/article/details/52414100
- 增加Controller的TestNG用例


2017090601
----------
最简单的Spring MVC版本

运行方式

	jetty:run

访问路径：

	http://localhost:8088/spring-mvc-demo/



----------



