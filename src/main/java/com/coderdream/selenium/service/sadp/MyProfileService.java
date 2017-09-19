package com.coderdream.selenium.service.sadp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

public class MyProfileService extends BaseSadpService {

	public Map<String, String> myProfile(WebDriver driver) {
		String linkText = "人力看板--我的人力档案";
		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.linkText(linkText)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(snapshot(method, driver), "进入【我的人力档案】页面：");
		return map;
	}

	public Map<String, String> myProfileBaseInfo(WebDriver driver) {
		String linkText = "基本信息";
		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.linkText(linkText)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(snapshot(method, driver), "进入【基本信息】页面：");
		return map;
	}

	public Map<String, String> updateMyProfileBaseInfo(WebDriver driver) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 修改基本信息【工作年限】
		String oldServiceYear = driver.findElement(By.id("serviceYear"))
						.getAttribute("value");
		Integer oldServiceYearValue = Integer.parseInt(oldServiceYear);
		oldServiceYearValue += 1;
		driver.findElement(By.id("serviceYear")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("serviceYear"))
						.sendKeys(oldServiceYearValue.toString());
		map.put(snapshot(method, driver), "修改基本信息【工作年限】，增加一年的工作经验：");

		// 点击【修改】按钮
		WebElement element = driver.findElement(By.id("profile-update-btn"));
		Coordinates coor = ((Locatable) element).getCoordinates();
		coor.inViewPort();
		map.put(snapshot(method, driver), "拖动滚动条，找到【修改】按钮：");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		element.click();
		map.put(snapshot(method, driver), "点击【修改】按钮，执行修改操作：");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		map.put(snapshot(method, driver), "点击弹出对话框的【确定】按钮，返回详细信息页面：");

		return map;
	}

	/**
	 * 新增技能
	 * 
	 * @param driver
	 * @param skillName
	 * @return
	 */
	public Map<String, String> addSkill(WebDriver driver, String skillName,
					String proficiencyName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		String linkText = "技能列表";
		// 根据传入的值选择下拉选单，点击该项目
		WebElement element = driver.findElement(By.linkText(linkText));
		element.click();

		driver.findElement(By.id("autocomplete_input_skill"))
						.sendKeys(skillName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击技能");

		driver.findElement(By.xpath("//span[text()='" + skillName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击【熟练程度下拉框】
		driver.findElement(By.id("proficiencyItem")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");

		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(
						By.xpath("//option[text()='" + proficiencyName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【" + proficiencyName + "】：");

		driver.findElement(By.id("skill-btn")).click();
		map.put(snapshot(method, driver), "点击【新增】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();

		linkText = "领域列表";
		map.putAll(scrollTo(driver, method, linkText));

		return map;
	}

	private Map<String, String> scrollTo(WebDriver driver,  
					String method, String linkText) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 滚动到领域
		WebElement element = driver.findElement(
						By.xpath("//span[text()='" + linkText + "']"));
		Coordinates coor = ((Locatable) element).getCoordinates();
		coor.inViewPort();

		map.put(snapshot(method, driver), "拖动滚动条，找到【" + linkText + "】：");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return map;
	}

	/**
	 * 修改技能
	 * 
	 * @param driver
	 * @param skillName
	 * @return
	 */
	public Map<String, String> editSkill(WebDriver driver, String skillName,
					String proficiencyName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 点击下拉选单
		driver.findElement(By.xpath("//span[text()='" + skillName + "']"))
						.click();
		map.put(snapshot(method, driver), "点击技能");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击【熟练程度下拉框】
		WebElement element = driver.findElement(By.id("proficiencyItem"));
		//map.put(snapshot2(method, element), "点击【熟练程度下拉框111】：");
		
		element.click();
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");

		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(
						By.xpath("//option[text()='" + proficiencyName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【" + proficiencyName + "】：");

		driver.findElement(By.id("skill-btn")).click();
		map.put(snapshot(method, driver), "点击【修改】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		
		String linkText = "领域列表";
		map.putAll(scrollTo(driver, method, linkText));

		return map;
	}

	/**
	 * 修改技能
	 * 
	 * @param driver
	 * @param skillName
	 * @return
	 */
	public Map<String, String> deleteSkill(WebDriver driver, String skillName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 点击下拉选单
		driver.findElement(By.xpath("//span[text()='" + skillName + "']"))
						.click();
		map.put(snapshot(method, driver), "点击技能");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("skill-delete-btn")).click();
		map.put(snapshot(method, driver), "点击【删除】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();

		String linkText = "领域列表";
		map.putAll(scrollTo(driver, method, linkText));

		return map;
	}

	public Map<String, String> editDomain(WebDriver driver, String domainName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 点击下拉选单
		driver.findElement(By.xpath("//span[text()='" + domainName + "']"))
						.click();
		map.put(snapshot(method, driver), "点击领域");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		driver.findElement(By.id("domain-btn")).click();
		map.put(snapshot(method, driver), "点击【修改】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		map.put(snapshot(method, driver), "点击【确定】按钮");

		return map;
	}
}
