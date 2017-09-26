package com.coderdream.pdrc;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EvaluateProjectService {

	private WebDriver driver;
	private String baseUrl;

	public EvaluateProjectService(String baseUrl) {
		System.setProperty("webdriver.firefox.bin", "C:/Firefox/firefox.exe");
		driver = new FirefoxDriver();
		this.setBaseUrl(baseUrl);
	}

	public void evaluateProject() {
		// 设置10秒
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 自定义浏览器窗口大小
		//driver.manage().window().setSize(new Dimension(1400, 1000));
		// 浏览器最大化
		driver.manage().window().maximize();
		driver.get(baseUrl);

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("B-26050");
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys("123");
		driver.findElement(By.cssSelector("input.templatemo-blue-button.width-100")).click();

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snapshot("case_0101_login.png");

		navigateToEvalPage();

		// 添加成员
		driver.findElement(By.xpath(".//*[@id='a_add']")).click();

		snapshot("case_0103_input_start.png");
		
		driver.findElement(By.xpath(".//*[@id='ProjectBookDateTime_0']")).sendKeys("2017-06-22");
		driver.findElement(By.xpath(".//*[@id='ProjectReleaseDateTime_0']")).sendKeys("2017-06-22");

		driver.findElement(By.xpath(".//*[@id='grid1']/tbody/tr/td[5]/input")).sendKeys("0.9");

		// 添加成员
		driver.findElement(By.xpath(".//*[@id='s2id_option0']/a/div/b")).click();

		WebElement optionElement = driver.findElement(By.xpath(".//*[@id='select2-drop']/div/input"));
		optionElement.click();
		optionElement.sendKeys("B-024");

		driver.findElement(By.xpath(".//*[@id='select2-drop']/ul/li/div")).click();

		snapshot("case_0104_input_finish.png");

		// 点击【保存】按钮
		((JavascriptExecutor) driver).executeScript("document.getElementById('a_save').click()");

		snapshot("case_0105_after_click_save.png");

		// 点击弹出的窗口中的【确定】按钮
		driver.findElement(By.xpath("html/body/div[6]/div[2]/div/a")).click();

		snapshot("case_0106_after_click_ok.png");

		// 跳出frame,进入default content;
		driver.switchTo().defaultContent();

		// 点击头像
		driver.findElement(By.xpath("html/body/div[1]/div/div/ul/li[3]/a/img")).click();

		// 点击注销
		driver.findElement(By.xpath("html/body/div[1]/div/div/ul/li[3]/ul/li[3]/a")).click();

		snapshot("case_0107_quit_system.png");
		// 退出浏览器
		driver.quit();
	}

	private void snapshot(String picname) {
		// 这里等待页面加载完成
		try {
			//Thread.sleep(1000);

			// 下面代码是得到截图并保存在D盘下
			File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(System.getProperty("user.dir") + "\\snapshot\\" + picname));

			//Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void navigateToEvalPage() {
		// 1 运营管理 click
		driver.findElement(By.xpath(".//*[@id='menulist']/li[6]/a/span[1]")).click();
		// 2 立项管理 click
		driver.findElement(By.xpath(".//*[@id='menulist']/li[6]/ul/li[1]/a/span[1]")).click();
		// 3 项目立项 click
		driver.findElement(By.xpath(".//*[@id='menulist']/li[6]/ul/li[1]/ul/li[2]/a")).click();

		// 进入id=""frame_content""的frame中
		driver.switchTo().frame("frame_content");
		snapshot("case_0102_enter_evaluate_project_page.png");

		WebElement child = driver.findElement(By.linkText("P06211624"));
		// System.out.println("child:\t" + child);

		WebElement parent = child.findElement(By.xpath("./.."));// 找到父元素
		// System.out.println("parent:\t" + parent);
		WebElement grandpa = parent.findElement(By.xpath("./.."));// 找到父元素
		// System.out.println("grandpa:\t" + grandpa);
		List<WebElement> brothers = grandpa.findElements(By.xpath("./*"));// 找到所有子元素

		for (WebElement webElement : brothers) {
			if (-1 != webElement.getText().indexOf("评价")) {
				List<WebElement> evalBrothersWebElement = webElement.findElements(By.xpath("./*"));// 找到所有子元素
				for (WebElement webElement2 : evalBrothersWebElement) {
					// System.out.println(
					// "brothers webElement:\t" + webElement2.getText() + "\t" +
					// webElement2.getTagName());
					if (webElement2.getText().equals("评价")) {
						webElement2.click();
					}
				}
			}
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
