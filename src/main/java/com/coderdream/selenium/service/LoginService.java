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
			// 类加载根路径
			String classPath = this.getClass().getResource("/").getPath();
			classPath = classPath.replace("WEB-INF/classes/", "");
			logger.debug("类加载根路径:" + classPath);
			// if(jetty)
			// filePath = System.getProperty("user.dir") +
			// "\\src\\main\\webapp\\snapshot\\"
			// + picname;
			filePath = classPath + "\\snapshot\\" + picname;
			logger.debug("保存文件路径:" + filePath);
			FileUtils.copyFile(screenShotFile, new File(filePath));

			// Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return picname;
	}
}
