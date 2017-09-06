package com.coderdream.selenium.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderdream.util.Constants;

public class LoginService {

	private static Logger logger = LoggerFactory.getLogger(LoginService.class);

	/**
	 * @param driver
	 * @param username
	 * @param password
	 */
	public List<String> login(WebDriver driver, String username,
					String password) {
		List<String> contents = new ArrayList<String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		String url = UriComponentsBuilder.fromHttpUrl(Constants.BASE_URL)
						.build().toUriString();
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
		
		contents.add(snapshot(method, driver));

		// login-btn
		// 提交
		driver.findElement(By.id("login-btn")).click();
		contents.add(snapshot(method, driver));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return contents;
	}

	private String snapshot(String picname, WebDriver driver) {
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		picname = sf.format(new Date()) + "_" + picname + ".png";
		logger.debug("picname\t {}", picname);
		String filePath = "";
		// 这里等待页面加载完成
		try {
			// Thread.sleep(1000);

			// 下面代码是得到截图并保存在D盘下
			File screenShotFile = ((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE);
			filePath = System.getProperty("user.dir") + "\\src\\main\\webapp\\snapshot\\"
							+ picname;
			FileUtils.copyFile(screenShotFile, new File(filePath));

			// Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return picname;
	}
}
