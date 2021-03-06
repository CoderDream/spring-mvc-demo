package com.coderdream.selenium.service.sadp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchTaskService extends BaseSadpService {

	public Map<String, String>  searchByParams(WebDriver driver, String linkText, String paramName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		// String linkText = "人力看板--人力查询-按技能查询";
		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.linkText(linkText)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();

		map.put(snapshot(method, driver), "进入【" + linkText + "】页面：");

		// 绝对匹配
		driver.findElement(By.xpath("//label[text()='" + paramName + "']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		map.put(snapshot(method, driver), "进入【" + linkText + "】页面：");

		// 
		driver.findElement(By.id("query-condition-btn")).click();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		map.put(snapshot(method, driver), "进入【" + linkText + "】页面：");
		return map;
	}
	
	public Map<String, String>  searchTaskByWorkPlace(WebDriver driver, String workPlace) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();


		// 绝对匹配
		driver.findElement(By.xpath("//label[text()='" + workPlace + "']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		map.put(snapshot(method, driver), "选中【" + workPlace + "】：");

		// 
		driver.findElement(By.id("task-query-btn")).click();
		map.put(snapshot(method, driver), "进入【查询结果】页面：");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return map;
	}
	
	// 
	
	public void selectProfileByName(WebDriver driver, String profileName) {
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();

		// 绝对匹配
		driver.findElement(By.xpath("//span[text()='" + profileName + "']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);
	}

	public void myProfileBaseInfo(WebDriver driver) {
		String linkText = "基本信息";
		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.linkText(linkText)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		snapshot(method, driver);
	}

	public void updateMyProfileBaseInfo(WebDriver driver) {
		// // profile-update-btn
		// String linkText = "基本信息";
		// // 根据传入的值选择下拉选单，点击该项目
		// driver.findElement(By.linkText(linkText)).click();
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		snapshot(method, driver);

		// 点击下拉选单
		driver.findElement(By.id("profile-update-btn")).click();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		snapshot(method, driver);
	}

	/**
	 * 返回我的人力看板
	 * 
	 * @param driver
	 */
	public void returnMyProfile(WebDriver driver) {
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		// 点击下拉选单
		driver.findElement(By.id("rtn-btn")).click();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);
	}

	// iOS
	public void editSkill(WebDriver driver, String skillName) {
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		// 点击下拉选单
		driver.findElement(By.xpath("//span[text()='" + skillName + "']")).click();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		driver.findElement(By.id("skill-btn")).click();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		snapshot(method, driver);
	}

	public void editDomain(WebDriver driver, String domainName) {
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		// 点击下拉选单
		driver.findElement(By.xpath("//span[text()='" + domainName + "']")).click();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		driver.findElement(By.id("domain-btn")).click();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		snapshot(method, driver);
	}
}
