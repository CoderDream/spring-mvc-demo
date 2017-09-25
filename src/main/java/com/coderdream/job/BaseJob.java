package com.coderdream.job;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderdream.util.CommonsMailUtil;
import com.coderdream.util.ConfigLoader;
import com.coderdream.util.Constants;
import com.coderdream.util.SystemTool;

public class BaseJob {

	private static Logger logger = LoggerFactory.getLogger(BaseJob.class);
	// String baseUrl;

	public WebDriver driver;

	public void setUp(String baseUrl) {
		String chromePath = "chrome.properties";

		// 类初始化后加载配置文件
		InputStream in = ConfigLoader.class.getClassLoader()
						.getResourceAsStream(chromePath);
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			logger.error("load mail setting error,pleace check the file path:"
							+ chromePath);
			logger.error(e.toString(), e);
		}
		String chromedriver = props.getProperty("chromedriver");
		String chrome = props
						.getProperty("chrome" + SystemTool.getMacAddress());
		logger.debug("chromedriver路径:" + chromedriver);
		logger.debug("chrome路径:" + chrome);

		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		String currentTime = sf.format(today);
		logger.debug("BaseJob at: " + currentTime);

		System.setProperty("webdriver.chrome.driver", chromedriver);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		// 设置Chrome不显示提示
		options.addArguments(Arrays.asList("--disable-infobars"));
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		// 设置Chrome.exe（二进制文件）的路径
		options.setBinary(chrome);

		// 初始化一个chrome浏览器实例，实例名称叫driver
		// ChromeDriver
		driver = new ChromeDriver(capabilities);

		// 自定义浏览器窗口大小
		// driver.manage().window().setSize(new Dimension(550, 730));

		// 最大化窗口
		// driver.manage().window().maximize();
		// 设置隐性等待时间
		// driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		// "pdrcurl"
		String url = UriComponentsBuilder
						.fromHttpUrl(props.getProperty(baseUrl)).build()
						.toUriString();
		// get()打开一个站点
		driver.get(url);
		// getTitle()获取当前页面title的值
		logger.debug("当前打开页面的标题是： {}", driver.getTitle());
	}

	public void sendMail(Map<String, String> map, String aSubject) {
		// set the html message
		StringBuilder aHtml = new StringBuilder("<html>");

		int i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
							+ entry.getValue());
			i++;
			String fileName = entry.getKey();
			String stepName = entry.getValue();
			// 绝对路径
			// String imgUrl = System.getProperty("user.dir") +
			// "\\src\\main\\webapp\\snapshot\\"
			// + fileName;
			aHtml.append("<div><span style=\"font-size:18px;\">" + i + "、"
							+ stepName + "</span></div>");
			// URL
			// String imgUrl = Constants.BASE_URL + "snapshot/" + fileName;
			// aHtml.append("<div style=\"width: 720px; height: 540px; border:
			// 1px solid #001\">");
			aHtml.append("<img src=\"" + fileName
							+ "\" style=\"border:solid 1px #999;\"><br>");
			// aHtml.append("</div><br>");
		}

		aHtml.append("</html>");
		logger.debug("testAndSendMailJob aHtml: {}", aHtml.toString());

		String aText = "Your email client does not support HTML messages";

		Map<String, String> addressMap = null;
		CommonsMailUtil.sendingHtmlFormattedEmail(aSubject, aHtml.toString(),
						aText, addressMap);
	}

	public void tearDown() {
		driver.quit();
		String processName = "chromedriver.exe";
		String command = "taskkill /f /im " + processName;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
