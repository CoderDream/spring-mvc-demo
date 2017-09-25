package com.coderdream.selenium.service.sadp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateTaskService extends BaseSadpService {

	public Map<String, String> enterToTaskPage(WebDriver driver,
					Dimension dimension) {
		driver.manage().window().setSize(dimension);
		String linkText = "任务看板--创建任务";
		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.linkText(linkText)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(snapshot(method, driver), "进入【" + linkText + "】页面：");
		return map;
	}

	public Map<String, String> createTaskByParams(WebDriver driver,
					String taskName, String taskDescription, String queryString,
					String projectName, String acceptanceStandard,
					String queryStringContactsName, String contactsName,
					String queryStringAcceptanceName, String acceptanceName,
					String email, String phone) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

		// 任务名称
		driver.findElement(By.id("taskName")).clear();
		driver.findElement(By.id("taskName")).sendKeys(taskName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 任务描述
		driver.findElement(By.id("taskDescription")).clear();
		driver.findElement(By.id("taskDescription")).sendKeys(taskDescription);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "设置任务名称和任务描述：");

		map.putAll(searchProjectName(driver, queryString, projectName));

		// 邮箱
		driver.findElement(By.id("contactEmail")).clear();
		driver.findElement(By.id("contactEmail")).sendKeys(email);

		// 电话
		driver.findElement(By.id("contactPhoneNumber")).clear();
		driver.findElement(By.id("contactPhoneNumber")).sendKeys(phone);

		// 设置联系人
		map.putAll(searchContacts(driver, queryStringContactsName,
						contactsName));

		// 设置验收人
		map.putAll(searchaAcceptance(driver, queryStringAcceptanceName,
						acceptanceName));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "设置验收标准：");

		// 验收标准
		driver.findElement(By.id("acceptanceStandard")).clear();
		driver.findElement(By.id("acceptanceStandard"))
						.sendKeys(acceptanceStandard);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "设置验收标准：");

		//
		driver.findElement(By.id("task-btn")).click();
		map.put(snapshot(method, driver), "点击【新增】按钮，执行新增操作：");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();

		// 自定义浏览器窗口大小(16,93)
		Dimension dimension = new Dimension(540 + 16, 480 + 93);
		map.put(snapshot(method, driver, dimension), "点击弹出对话框的【确定】按钮：");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * @param driver
	 * @param queryString
	 * @param projectName
	 */
	private Map<String, String> searchProjectName(WebDriver driver,
					String queryString, String projectName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
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
		map.put(snapshot(method, driver), "输入查询条件：");

		// 选择查询结果
		driver.findElement(By.xpath("//span[text()='" + projectName + "']"))
						.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "选择查询结果：");

		return map;
	}

	/**
	 * @param driver
	 * @param queryString
	 * @param projectName
	 */
	private Map<String, String> searchContacts(WebDriver driver,
					String queryString, String contactsName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 清空输入框
		driver.findElement(By.id("autocomplete_input_contacts")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 输入查询条件
		driver.findElement(By.id("autocomplete_input_contacts"))
						.sendKeys(queryString);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		map.put(snapshot(method, driver), "输入查询条件：");

		// 选择查询结果
		driver.findElement(By.xpath("//span[text()='" + contactsName + "']"))
						.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "选择查询结果：");

		return map;
	}

	private Map<String, String> searchaAcceptance(WebDriver driver,
					String queryString, String acceptanceName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 清空输入框
		driver.findElement(By.id("autocomplete_input_acceptance")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 输入查询条件
		driver.findElement(By.id("autocomplete_input_acceptance"))
						.sendKeys(queryString);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		map.put(snapshot(method, driver), "输入查询条件：");

		// 选择查询结果
		driver.findElement(By.xpath("//span[text()='" + acceptanceName + "']"))
						.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "选择查询结果：");

		return map;
	}

	//

	public void selectProfileByName(WebDriver driver, String profileName) {
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

		// 绝对匹配
		driver.findElement(By.xpath("//span[text()='" + profileName + "']"))
						.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot(method, driver);
	}

	public Map<String, String> enterToCreateTaskItemPage(WebDriver driver,
					Dimension dimension) {
		driver.manage().window().setSize(dimension);
		String linkText = "任务所需资源清单";
		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.linkText(linkText)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(snapshot(method, driver), "进入【" + linkText + "】页面：");
		return map;
	}

	public Map<String, String> createTaskItemByParams(WebDriver driver,
					String taskItemDescription, String enginnerAmount,
					String workPlaceName, String serviceYear, String bsm,
					String planStartDateString, String planEndDateString) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

		// 任务描述
		driver.findElement(By.id("taskItemDescription")).clear();
		driver.findElement(By.id("taskItemDescription"))
						.sendKeys(taskItemDescription);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 所需人数
		driver.findElement(By.id("enginnerAmount")).clear();
		driver.findElement(By.id("enginnerAmount")).sendKeys(enginnerAmount);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "设置任务描述，所需人数：");

		// 工作城市
		Select selectWorkPlace = new Select(
						driver.findElement(By.id("workPlaceItem")));
		selectWorkPlace.selectByVisibleText(workPlaceName);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【工作城市下拉框】：");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【" + workPlaceName + "】：");

		// 工作年限
		driver.findElement(By.id("serviceYear")).clear();
		driver.findElement(By.id("serviceYear")).sendKeys(serviceYear);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// bsm
		driver.findElement(By.id("bsm")).clear();
		driver.findElement(By.id("bsm")).sendKeys(bsm);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 预计任务开始日期
		js.executeScript(
						"document.getElementById(\"planStartDateString\").value=\""
										+ planStartDateString + "\";");
		js.executeScript(
						"document.getElementById(\"planEndDateString\").value=\""
										+ planEndDateString + "\";");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "设置工作年限，bsm，预计任务开始日期，预计任务结束日期：");

		//
		driver.findElement(By.id("taskitem-btn")).click();
		map.put(snapshot(method, driver), "点击【新增】按钮，执行新增操作：");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();

		// 自定义浏览器窗口大小(16,93)
		Dimension dimension = new Dimension(540 + 16, 580 + 93);
		map.put(snapshot(method, driver, dimension), "点击弹出对话框的【确定】按钮：");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 技能要求
	 * 
	 * @param driver
	 * @param dimension
	 * @return
	 */
	public Map<String, String> enterToCreateSkillPage(WebDriver driver,
					Dimension dimension) {
		driver.manage().window().setSize(dimension);
		String linkText = "技能要求";
		return enterToPageByLinkText(driver, dimension, linkText);
	}

	/**
	 * 技能要求
	 * 
	 * @param driver
	 * @param dimension
	 * @return
	 */
	public Map<String, String> enterToCreateDomainPage(WebDriver driver,
					Dimension dimension) {
		driver.manage().window().setSize(dimension);
		String linkText = "领域要求";
		return enterToPageByLinkText(driver, dimension, linkText);
	}
	
	// TODO

	public void myProfileBaseInfo(WebDriver driver) {
		String linkText = "基本信息";
		// 根据传入的值选择下拉选单，点击该项目
		driver.findElement(By.linkText(linkText)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
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
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
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
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
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
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 点击下拉选单
		driver.findElement(By.xpath("//span[text()='" + skillName + "']"))
						.click();
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
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 点击下拉选单
		driver.findElement(By.xpath("//span[text()='" + domainName + "']"))
						.click();
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
