package com.coderdream.selenium.service.sadp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ProjectAuditService extends BaseSadpService {

	public Map<String, String> newAudit(WebDriver driver) {
		// String linkText = "新增项目审计";
		// 根据传入的值选择下拉选单，点击该项目 new_audit
		// driver.findElement(By.linkText(linkText)).click();
		driver.findElement(By.id("new_audit")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(snapshot(method, driver), "进入【新增项目审计】页面：");
		return map;
	}

	public Map<String, String> queryProject(WebDriver driver,
					String projectName) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();

		driver.findElement(By.id("autocomplete_input_project"))
						.sendKeys(projectName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "输入项目名称" + projectName + ":");

		driver.findElement(By.xpath("//span[text()='" + projectName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "选择项目名称" + projectName + ":");

		// 点击【修改】按钮
		driver.findElement(By.id("project-btn")).click();
		map.put(snapshot(method, driver), "点击【查询】按钮，执行修改操作：");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return map;
	}

	public Map<String, String> projectAuditBaseInfo(WebDriver driver) {
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

	public Map<String, String> addAudit(WebDriver driver) {
		String linkText = "目标合理性";
		// 根据传入的值选择下拉选单，点击该项目 new_audit
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

	public Map<String, String> saveAudit(WebDriver driver, String auditName,
					String auditContent, Integer auditState) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		// 点击【熟练程度下拉框】
		WebElement element = driver.findElement(By.id("auditItem"));
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
		driver.findElement(By.xpath("//option[text()='" + auditName + "']"))
						.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【" + auditName + "】：");

		// 评价内容auditContent
		driver.findElement(By.id("auditContent")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("auditContent")).sendKeys(auditContent);
		map.put(snapshot(method, driver), "修改评价内容为" + auditContent + "：");

		// Radio控件被Label覆盖，所以直接点击Label控件
		String xpath1 = "//input[@type='radio'][@value='" + auditState + "']";
		System.out.println("xpath1: " + xpath1);
		String xpath = null;
		if (1 == auditState) {
			xpath = "//label[@for='pass']";
		} else {
			xpath = "//label[@for='fail']";
		}
		WebElement stateElement = driver.findElement(By.xpath(xpath));

		// 选择某个单选框
		stateElement.click();

		driver.findElement(By.id("audit-btn")).click();
		map.put(snapshot(method, driver), "点击【修改】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "返回项目评价页面");

		return map;
	}

	public Map<String, String> updateAudit(WebDriver driver, String auditName,
					String auditContent, Integer auditState, String staffName) {
		// String linkText = "目标合理性";// TODO

		String xpath3 = ".//*[@id='projectAudit']/div/ul/li[6]/ul[1]/li[1]/a/span";
		String xpath4 = ".//*[@id='projectAudit']/div/ul/li[6]/ul[2]/li[2]/span[2]/span";
		WebElement xpath1Element = driver.findElement(By.xpath(xpath3));
		System.out.println(xpath1Element.getText());
		
		
		List<WebElement> buttonElements = driver.findElements(
						By.xpath("//span[text()='" + auditName + "']"));
		for (WebElement webElement : buttonElements) {
			webElement.getCssValue("");
		}

		WebElement parent = driver.findElement(By.id("父元素定位方式,id是举例"));
		WebElement son = parent.findElement(By.id("子元素定位方式,id是举例"));
		List<WebElement> list = parent.findElements(By.id("多个子元素"));

		// 根据传入的值选择下拉选单，点击该项目 new_audit
		driver.findElement(By.linkText(auditName)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String method = Thread.currentThread().getStackTrace()[1]
						.getMethodName();
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(snapshot(method, driver), "进入【" + auditName + "】页面：");

		// 评价内容auditContent
		driver.findElement(By.id("auditContent")).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("auditContent")).sendKeys(auditContent);
		map.put(snapshot(method, driver), "修改评价内容为" + auditContent + "：");

		// Radio控件被Label覆盖，所以直接点击Label控件
		String xpath1 = "//input[@type='radio'][@value='" + auditState + "']";
		System.out.println("xpath1: " + xpath1);
		String xpath = null;
		if (1 == auditState) {
			xpath = "//label[@for='pass']";
		} else {
			xpath = "//label[@for='fail']";
		}
		WebElement stateElement = driver.findElement(By.xpath(xpath));

		// 选择某个单选框
		stateElement.click();

		driver.findElement(By.id("audit-btn")).click();
		map.put(snapshot(method, driver), "点击【修改】按钮");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击确定按钮
		driver.findElement(By.xpath("//span[text()='确定']")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "返回项目评价页面");

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
		driver.findElement(By.linkText(linkText)).click();
		map.put(snapshot(method, driver), "进入技能新增页面：");

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

		// 点击【熟练程度下拉框】
		// String linkText2 = "--请选择熟练程度--";
		// driver.findElement(By.xpath("//span[text()='" + linkText2 + "']"))
		// .click();

		// driver.findElement(By.id("proficiencyItem-button")).click();
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		// driver.findElement(By.id("proficiency-button")).click();
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");
		driver.findElement(By.id("proficiencyItem")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");

		Select selectRoleName = new Select(
						driver.findElement(By.id("proficiencyItem")));
		// selectRoleName.c
		// driver.findElement(By.id("proficiencyItem")).click();
		// map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");
		// selectRoleName.selectByValue(proficiencyName); //未审核
		selectRoleName.selectByVisibleText(proficiencyName);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		map.put(snapshot(method, driver), "点击【熟练程度下拉框】：");

		// // 根据传入的值选择下拉选单，点击该项目
		// driver.findElement(
		// By.xpath("//option[text()='" + proficiencyName + "']"))
		// .click();

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

	private Map<String, String> scrollTo(WebDriver driver, String method,
					String linkText) {
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

	/**
	 * 获取选项列表
	 * 
	 * @return
	 */
	public List<WebElement> getOptions(WebElement element) {
		return element.findElements(By.tagName("option"));
	}

	/**
	 * 根据select的value来选择
	 * 
	 * @param value
	 */
	public void setOptionByValue(WebElement element, String value) {
		for (WebElement op : getOptions(element)) {
			if (op.getAttribute("value").equals(value)) {
				op.click();
				return;
			}
		}
		throw new NoSuchElementException(
						"Cannot locate an element in Select-setOptionByValue ");
	}

	/**
	 * 根据显示的文本来选择
	 * 
	 * @param text
	 */
	public void setOptionByText(WebElement element, String text) {
		for (WebElement op : getOptions(element)) {
			if (op.getText().equals(text)) {
				op.click();
				return;
			}
		}
		throw new NoSuchElementException(
						"Cannot locate an element in Select-setOptionByText ");
	}
}
