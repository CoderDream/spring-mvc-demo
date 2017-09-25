package com.coderdream.job.sadp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.job.BaseJob;
import com.coderdream.selenium.service.sadp.CreateTaskService;

public class CreateTaskServiceJob extends BaseJob {

	private static Logger logger = LoggerFactory
					.getLogger(CreateTaskServiceJob.class);

	private CreateTaskService createTaskService;

	private String baseUrl = "pdrcurl";

	/**
	 * 
	 */
	public void ceateTask() {
		logger.debug("ceateTask");
		super.setUp(baseUrl);
		createTaskService = new CreateTaskService();
		Map<String, String> map = new LinkedHashMap<String, String>();
		String roleName = "PM";
		String staffName = "[B-16916]邵京国";

		// 登陆
		createTaskService.login(driver, roleName, staffName);

		// 进入【任务看板--创建任务】页面
		//
		// 自定义浏览器窗口大小(16,93)
		Dimension dimension = new Dimension(540 + 16, 865 + 93);
		map.putAll(createTaskService.enterToTaskPage(driver, dimension));

		// 进入【我的基本信息】页面
		String taskName = "UPET-统一训战6月版本-V2R02I02";
		String taskDescription = "UPET-统一训战6月版本-V2R02I02";
		String acceptanceStandard = "华为验收标准";
		String queryString = "UPET-统一训战6月版本";
		String projectName = "UPET-统一训战6月版本-V2R02I02";
		String queryStringContactsName = "1691";
		String contactsName = "[B-16916]邵京国";
		String queryStringAcceptanceName = "邵京";
		String acceptanceName = "[B-16916]邵京国";
		String email = "abc@qq.com";
		String phone = "13999999999";
		map.putAll(createTaskService.createTaskByParams(driver, taskName,
						taskDescription, queryString, projectName,
						acceptanceStandard, queryStringContactsName,
						contactsName, queryStringAcceptanceName, acceptanceName,
						email, phone));

		// 自定义浏览器窗口大小(16,93)
		dimension = new Dimension(540 + 16, 865 + 93);
		map.putAll(createTaskService.enterToCreateTaskItemPage(driver,
						dimension));

		String taskItemDescription = "UPET-统一训战6月版本-V2R02I02 集成测试";
		String enginnerAmount = "15";
		String workPlaceName = "武汉";
		String serviceYear = "10";
		String bsm = "12.5";
		String planStartDateString = "2017-08-01";
		String planEndDateString = "2017-10-01";
		map.putAll(createTaskService.createTaskItemByParams(driver,
						taskItemDescription, enginnerAmount, workPlaceName,
						serviceYear, bsm, planStartDateString,
						planEndDateString));

		// 自定义浏览器窗口大小(16,93)
		dimension = new Dimension(540 + 16, 365 + 93);
		map.putAll(createTaskService.enterToCreateSkillPage(driver, dimension));

		// 修改技能信息后自动返回
		String skillName = "iOS";
		String proficiencyName = "熟练";
		map.putAll(createTaskService.addSkill(driver, skillName,
						proficiencyName));

		proficiencyName = "精通";
		map.putAll(createTaskService.editSkill(driver, skillName,
						proficiencyName));

		map.putAll(createTaskService.deleteSkill(driver, skillName));
		

		// 自定义浏览器窗口大小(16,93)
		dimension = new Dimension(540 + 16, 365 + 93);
		map.putAll(createTaskService.enterToCreateDomainPage(driver, dimension));
		
		// 修改领域信息后自动返回
		String domainName = "CRM";
		String experienceName = "部分精通";
		map.putAll(createTaskService.addDomain(driver, domainName,
						experienceName));

		experienceName = "领域专家";
		map.putAll(createTaskService.editDomain(driver, domainName,
						experienceName));

		map.putAll(createTaskService.deleteDomain(driver, domainName));


		 // 我发布的任务
		// map.putAll(createTaskService.updateMyProfileBaseInfo(driver));

		// 修改技能信息后自动返回 【任务所需资源清单】
		// String skillName = "iOS";
		// map.putAll(viewCreateTaskService.editSkill(driver, skillName));

		String aSubject = "任务看板--创建任务";
		sendMail(map, aSubject);

		super.tearDown();
	}

	// public void operateProfileSkillInfo() {
	// logger.debug("getMyProfile");
	// super.setUp(baseUrl);
	// createTaskService = new CreateTaskService();
	// Map<String, String> map = new LinkedHashMap<String, String>();
	// String roleName = "普通员工";
	// String staffName = "[B-30728]吕帅";
	//
	// // 登陆
	// createTaskService.login(driver, roleName, staffName);
	//
	// // 进入【我的人力看板】页面
	// map.putAll(createTaskService.myProfile(driver));
	//
	// // 修改技能信息后自动返回
	// String skillName = "iOS";
	// String proficiencyName = "熟练";
	// map.putAll(createTaskService.addSkill(driver, skillName,
	// proficiencyName));
	//
	// proficiencyName = "精通";
	// map.putAll(createTaskService.editSkill(driver, skillName,
	// proficiencyName));
	//
	// map.putAll(createTaskService.deleteSkill(driver, skillName));
	//
	// String aSubject = "自动化测试--修改人力档案的技能信息";
	// sendMail(map, aSubject);
	//
	// super.tearDown();
	// }

	public static void main(String[] args) {
		// new CreateTaskServiceJob().getMyProfile();
		new CreateTaskServiceJob().ceateTask();

		// logger.debug(System.getProperty("user.dir"));
		// String processName = "chromedriver.exe";
		// String command = "taskkill /f /im " + processName;
		// try {
		// Runtime.getRuntime().exec(command);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		jvmPid();
	}

	public static void jvmPid() {
		String processName = "chromedriver.exe";
		try {
			String[] cmmd = { "cmd", "/c",
							"FOR /F \"tokens=2,3*\"; %i  in ('tasklist /nh ^| find \""
											+ processName
											+ "\"') do @echo %i" };
			String str = null;
			Process process = Runtime.getRuntime().exec(cmmd);
			BufferedReader br = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
