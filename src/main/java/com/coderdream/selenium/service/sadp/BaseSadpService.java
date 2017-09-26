package com.coderdream.selenium.service.sadp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.util.Constants;

public class BaseSadpService {

	private static Logger logger = LoggerFactory
					.getLogger(BaseSadpService.class);

	/**
	 * @param driver
	 * @param roleName
	 * @param staffName
	 */
	public void login(WebDriver driver, String roleName, String staffName) {
		// 点击下拉选单
		driver.findElement(By.id("roleName")).click();

		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.xpath("//option[text()='" + roleName + "']"))
						.click();

		// snapshot(method, driver);
		// 设置值
		Select selectRoleName = new Select(
						driver.findElement(By.id("roleName")));
		// snapshot(method, driver);
		selectRoleName.selectByVisibleText(roleName);

		// 点击下拉选单
		driver.findElement(By.id("staff")).click();

		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.xpath("//option[text()='" + staffName + "']"))
						.click();
		// 设置值
		Select selectUser = new Select(driver.findElement(By.id("staff")));
		selectUser.selectByVisibleText(staffName);

		driver.findElement(By.id("submit")).click();
	}

	public Map<String, String> enterToAddAuditPage(WebDriver driver) {
		driver.findElement(By.id("new_audit")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		String fileName = snapshot(method, driver);
		Map<String, String> map = new HashMap<String, String>();
		map.put("登陆【新建审计】页面", fileName);
		return map;
	}

	public void enterToAuditPage(WebDriver driver, String projectName) {
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 绝对匹配
		driver.findElement(By.xpath("//span[text()='" + projectName + "']"))
						.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);
	}

	/**
	 * @param driver
	 * @param queryString
	 * @param projectName
	 */
	public void setProjectInfoAndEnterToAuditPage(WebDriver driver,
					String queryString, String projectName) {
		// 清空输入框
		driver.findElement(By.id("autocomplete_input_project")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 输入查询条件
		driver.findElement(By.id("autocomplete_input_project"))
						.sendKeys(queryString);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		snapshot(method, driver);

		// 选择查询结果
		driver.findElement(By.xpath("//li[text()='" + projectName + "']"))
						.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);

		// 点击查询按钮
		driver.findElement(By.id("project-btn")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);
	}

	/**
	 * 根据审计类型名称，进入新增审计科目页面
	 * 
	 * @param driver
	 * @param typeName
	 */
	public void enterToAddAuditItemPage(WebDriver driver, String typeName) {
		// 链接文字
		driver.findElement(By.linkText(typeName)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		snapshot(method, driver);
	}

	public void addAuditItem(WebDriver driver, String auditItemName,
					String auditContent, Boolean passFlag) {
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 点击下拉选单
		driver.findElement(By.id("auditItem")).click();
		snapshot(method, driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);

		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.xpath("//option[text()='" + auditItemName + "']"))
						.click();
		snapshot(method, driver);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 设置值
		Select selectRoleName = new Select(
						driver.findElement(By.id("auditItem")));
		selectRoleName.selectByVisibleText(auditItemName);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);

		// 设置评价内容
		driver.findElement(By.id("auditContent")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("auditContent")).sendKeys(auditContent);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);

		// pass or fail 通过Label点击
		if (passFlag) {
			driver.findElement(By.id("label_pass")).click();
		} else {
			driver.findElement(By.id("label_fail")).click();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);

		// 提交
		driver.findElement(By.id("audit-btn")).click();
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
	 * 质量目标合理性
	 * 
	 * @param driver
	 * @param auditItemName
	 */
	public void enterToEditAuditItemPage(WebDriver driver,
					String auditItemName) {
		// 链接文字
		driver.findElement(By.linkText(auditItemName)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		snapshot(method, driver);
	}

	public void editAuditItem(WebDriver driver, String auditContent,
					Boolean passFlag) {
		// 设置评价内容
		driver.findElement(By.id("auditContent")).clear();
		driver.findElement(By.id("auditContent")).sendKeys(auditContent);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		snapshot(method, driver);

		// pass or fail 通过Label点击
		if (passFlag) {
			driver.findElement(By.id("label_pass")).click();
		} else {
			driver.findElement(By.id("label_fail")).click();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);

		// 提交
		driver.findElement(By.id("audit-btn")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);

		// 点击确定按钮
		// 绝对匹配
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);
	}

	/**
	 * @param driver
	 */
	public void deleteAuditItem(WebDriver driver) {
		// 提交
		driver.findElement(By.id("delete-btn")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		snapshot(method, driver);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 点击确定按钮
		// 绝对匹配
		driver.findElement(By.xpath("//span[text()='确定']")).click();

		snapshot(method, driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		snapshot(method, driver);
	}

	/**
	 * @param driver
	 * @param dimension
	 * @param linkText
	 * @return
	 */
	public Map<String, String> enterToPageByLinkText(WebDriver driver,
					Dimension dimension, String linkText) {
		driver.manage().window().setSize(dimension);
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
		map.put(snapshot(method, driver), "进入【" + linkText + "】页面：");
		return map;
	}

	public Map<String, String> enterToPageBySpanText(WebDriver driver,
					Dimension dimension, String spanText) {
		driver.manage().window().setSize(dimension);

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='" + spanText + "']"))
						.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(snapshot(method, driver), "进入【" + spanText + "】页面：");
		return map;
	}

	// UPET-统一训战6月版本-V2R02I02

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
		driver.findElement(By.id("autocomplete_input_skill"))
						.sendKeys(skillName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "输入技能" + skillName + ":");

		driver.findElement(By.xpath("//span[text()='" + skillName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "选择技能" + skillName + ":");

		Select selectRoleName = new Select(
						driver.findElement(By.id("proficiencyItem")));
		// selectRoleName.selectByValue(proficiencyName); //未审核
		selectRoleName.selectByVisibleText(proficiencyName);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");

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
		// map.put(snapshot2(method, element), "点击【熟练程度下拉框111】：");

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
		Dimension dimension = new Dimension(540 + 16, 665 + 93);
		map.put(snapshot(method, driver, dimension), "点击【确定】按钮");

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

		Dimension dimension = new Dimension(540 + 16, 665 + 93);
		map.put(snapshot(method, driver, dimension), "点击【确定】按钮");

		return map;
	}

	/**
	 * 新增领域
	 * 
	 * @param driver
	 * @param domainName
	 * @return
	 */
	public Map<String, String> addDomain(WebDriver driver, String domainName,
					String experienceName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		driver.findElement(By.id("autocomplete_input_domain"))
						.sendKeys(domainName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "输入领域" + domainName + ":");

		driver.findElement(By.xpath("//span[text()='" + domainName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "选择领域" + domainName + ":");

		Select selectRoleName = new Select(
						driver.findElement(By.id("experienceItem")));
		// selectRoleName.selectByValue(experienceName); //未审核
		selectRoleName.selectByVisibleText(experienceName);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【" + experienceName + "】：");

		driver.findElement(By.id("domain-btn")).click();
		map.put(snapshot(method, driver), "点击【新增】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();

		return map;
	}

	/**
	 * 修改领域
	 * 
	 * @param driver
	 * @param domainName
	 * @return
	 */
	public Map<String, String> editDomain(WebDriver driver, String domainName,
					String experienceName) {
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

		// 点击【熟练程度下拉框】
		WebElement element = driver.findElement(By.id("experienceItem"));
		// map.put(snapshot2(method, element), "点击【熟练程度下拉框111】：");

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
						By.xpath("//option[text()='" + experienceName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【" + experienceName + "】：");

		driver.findElement(By.id("domain-btn")).click();
		map.put(snapshot(method, driver), "点击【修改】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		Dimension dimension = new Dimension(540 + 16, 665 + 93);
		map.put(snapshot(method, driver, dimension), "点击【确定】按钮");

		return map;
	}

	/**
	 * 修改领域
	 * 
	 * @param driver
	 * @param domainName
	 * @return
	 */
	public Map<String, String> deleteDomain(WebDriver driver,
					String domainName) {
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

		driver.findElement(By.id("domain-delete-btn")).click();
		map.put(snapshot(method, driver), "点击【删除】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();

		Dimension dimension = new Dimension(540 + 16, 665 + 93);
		map.put(snapshot(method, driver, dimension), "点击【确定】按钮");

		return map;
	}

	public String snapshot(String picname, WebDriver driver) {
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		picname = sf.format(new Date()) + "_" + picname + ".png";
		logger.debug("picname\t {}", picname);

		String fileName = System.getProperty("user.dir") + "\\snapshot\\"
						+ picname;

		// 这里等待页面加载完成
		try {
			// 下面代码是得到截图并保存在D盘下
			File screenShotFile = ((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileName;
	}

	public String snapshot(String picname, WebDriver driver,
					Dimension dimension) {
		driver.manage().window().setSize(dimension);
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		picname = sf.format(new Date()) + "_" + picname + ".png";
		logger.debug("picname\t {}", picname);

		String fileName = System.getProperty("user.dir") + "\\snapshot\\"
						+ picname;

		// 这里等待页面加载完成
		try {
			// 下面代码是得到截图并保存在D盘下
			File screenShotFile = ((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileName;
	}

	public String snapshot2(String picname, WebElement element) {
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		picname = sf.format(new Date()) + "_" + picname + ".png";
		logger.debug("picname\t {}", picname);

		String fileName = System.getProperty("user.dir") + "\\snapshot\\"
						+ picname;

		// 这里等待页面加载完成
		try {
			// Thread.sleep(1000);

			// 下面代码是得到截图并保存在D盘下
			File screenShotFile = element.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(fileName));

			// Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileName;
	}

}