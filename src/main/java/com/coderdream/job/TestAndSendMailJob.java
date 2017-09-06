package com.coderdream.job;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.selenium.service.LoginService;
import com.coderdream.util.CommonsMailUtil;
import com.coderdream.util.Constants;

public class TestAndSendMailJob {

	private static Logger logger = LoggerFactory
					.getLogger(TestAndSendMailJob.class);

	public void testAndSendMail() {

		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		String currentTime = sf.format(today);
		logger.debug("testAndSendMailJob at: " + currentTime);

		// 类加载根路径
		String classPath = this.getClass().getResource("/").getPath();
		classPath = classPath.replace("WEB-INF/classes/", "");
		logger.debug("类加载根路径:" + classPath);

		// 类加载根路径
		URL xmlPath = this.getClass().getClassLoader().getResource("");

		// 类所在工程根路径
		String proClassPath = this.getClass().getResource("").getPath();

		// 项目服务器脚本文件路径
		File directory = new File("");// 参数为空
		String proRootPath = "";
		try {
			proRootPath = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 项目服务器脚本文件路径
		String proPath = System.getProperty("user.dir");

		// 获取所有的类路径 包括jar包的路径
		String allClassPath = System.getProperty("java.class.path");

		// 项目部署的路径
		// String path =
		// request.getSession().getServletContext().getRealPath("/");

		logger.debug("类加载根路径:" + xmlPath);
		logger.debug("类所在工程路径:" + proClassPath);
		logger.debug("项目服务器脚本文件路径:" + proRootPath);
		logger.debug("项目服务器脚本文件路径:" + proPath);
		logger.debug("项目部署的路径:" + allClassPath);
		// logger.debug("获取所有的类路径:" + path );
		String driverURL = classPath + "chromedriver.exe";
		logger.debug("driverURL的路径:" + driverURL);
		System.setProperty("webdriver.chrome.driver", driverURL);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments(Arrays.asList("--disable-infobars"));
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		// 初始化一个chrome浏览器实例，实例名称叫driver
		ChromeDriver driver = new ChromeDriver(capabilities);

		// 自定义浏览器窗口大小
		driver.manage().window().setSize(new Dimension(720, 540));

		// 最大化窗口
		// driver.manage().window().maximize();
		// 设置隐性等待时间
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		String username = currentTime;
		String password = "123456";
		LoginService loginService = new LoginService();
		List<String> contents = loginService.login(driver, username, password);

		// 关闭并退出浏览器
		driver.quit();

		String aSubject = "自动化测试登陆功能";

		// String fileName = "20170906115041194_luanchLogin.png";

		// set the html message
		StringBuilder aHtml = new StringBuilder("<html>");

		for (String fileName : contents) {
			// 绝对路径
			// String imgUrl = System.getProperty("user.dir") +
			// "\\src\\main\\webapp\\snapshot\\"
			// + fileName;

			// URL
			String imgUrl = Constants.BASE_URL + "snapshot/" + fileName;
			aHtml.append("<div style=\"width: 720px; height: 540px; border: 1px solid #001\">");
			aHtml.append("<img src=\"" + imgUrl + "\">");
			aHtml.append("</div><br>");
		}

		aHtml.append("</html>");
		logger.debug("testAndSendMailJob aHtml: {}", aHtml.toString());

		String aText = "Your email client does not support HTML messages";

		Map<String, String> addressMap = null;
		CommonsMailUtil.sendingHtmlFormattedEmail(aSubject, aHtml.toString(),
						aText, addressMap);
	}

	public static void main(String[] args) {
		new TestAndSendMailJob().testAndSendMail();

		// logger.debug(System.getProperty("user.dir"));
	}
}
