package com.coderdream.job;

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

		System.setProperty("webdriver.chrome.driver",
						Constants.DRIVER_LOCATION + "\\chromedriver.exe");
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

		// System.out.println(System.getProperty("user.dir"));
	}
}
