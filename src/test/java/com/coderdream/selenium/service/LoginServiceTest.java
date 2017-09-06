package com.coderdream.selenium.service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.coderdream.util.Constants;

public class LoginServiceTest {

	@Test
	public void login() {
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

		String username = "admin";
		String password = "123456";
		LoginService LoginService = new LoginService();
		LoginService.login(driver, username, password);

		// 关闭并退出浏览器
		driver.quit();
	}
}
