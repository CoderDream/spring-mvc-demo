package com.coderdream.selenium;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;

import com.coderdream.util.Constants;

public class LuanchChromeTest {

	private static Logger logger = LoggerFactory.getLogger(LuanchChrome.class);

	@Test
	public void luanchChrome() {
		System.setProperty("webdriver.chrome.driver",
						Constants.DRIVER_LOCATION + "\\chromedriver.exe");

		// 初始化一个chrome浏览器实例，实例名称叫driver
		WebDriver driver = new ChromeDriver();
		// 最大化窗口
		driver.manage().window().maximize();
		// 设置隐性等待时间
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		// get()打开一个站点
		driver.get("https://www.baidu.com");
		// getTitle()获取当前页面title的值
		logger.debug("当前打开页面的标题是： {}", driver.getTitle());

		// 关闭并退出浏览器
		driver.quit();
	}

	@Test
	public void luanchLogin() {
		System.setProperty("webdriver.chrome.driver",
						Constants.DRIVER_LOCATION + "\\chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments(Arrays.asList("--disable-infobars", "--no-sandbox",
						"--ignore-certificate-errors", "--homepage=about:blank",
						"--no-first-run"));
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		ChromeDriver driver = new ChromeDriver(capabilities);
		// 初始化一个chrome浏览器实例，实例名称叫driver
		// WebDriver driver = new ChromeDriver();

		// options = webdriver.ChromeOptions()
		// options.add_argument('disable-infobars')
		// driver = webdriver.Chrome(chrome_options=options)

		// 自定义浏览器窗口大小
		driver.manage().window().setSize(new Dimension(720, 540));

		// 最大化窗口
		// driver.manage().window().maximize();
		// 设置隐性等待时间
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		String username = "admin";
		String password = "123456";
		String url = UriComponentsBuilder.fromHttpUrl(Constants.BASE_URL).build()
						.toUriString();
		// get()打开一个站点
		driver.get(url);
		// getTitle()获取当前页面title的值
		logger.debug("当前打开页面的标题是： {}", driver.getTitle());

		// 设置username
		driver.findElement(By.id("username")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("username")).sendKeys(username);

		// 设置password
		driver.findElement(By.id("password")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("password")).sendKeys(password);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		snapshot(method, driver);

		// login-btn
		// 提交
		driver.findElement(By.id("login-btn")).click();
		snapshot(method, driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 关闭并退出浏览器
		driver.quit();
	}

	public void snapshot(String picname, WebDriver driver) {
		SimpleDateFormat sf = new SimpleDateFormat(Constants.COMPLEX_DATE_FORMAT2);
		picname = sf.format(new Date()) + "_" + picname + ".png";
		logger.debug("picname\t {}", picname);

		// 这里等待页面加载完成
		try {
			// Thread.sleep(1000);

			// 下面代码是得到截图并保存在D盘下
			File screenShotFile = ((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile,
							new File(System.getProperty("user.dir")
											+ "\\snapshot\\" + picname));

			// Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
