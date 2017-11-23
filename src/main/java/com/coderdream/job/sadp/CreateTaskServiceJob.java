package com.coderdream.job.sadp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.job.BaseJob;
import com.coderdream.selenium.service.sadp.CreateTaskService;
import com.coderdream.selenium.service.sadp.SearchTaskService;
import com.coderdream.util.Constants;

public class CreateTaskServiceJob extends BaseJob {

	private static Logger logger = LoggerFactory
					.getLogger(CreateTaskServiceJob.class);

	private CreateTaskService createTaskService;

	private SearchTaskService searchTaskService;

	private String baseUrl = "pdrcurl";

	private static String taskName = "";
	private static String taskItemDescription = "";

	static {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		String timeStr = sf.format(date);
		taskName = "UPET-统一训战6月版本-V2R02I02" + timeStr;
		taskItemDescription = "UPET-统一训战6月版本-V2R02I02 集成测试" + timeStr;
	}

	/**
	 * 
	 */
	public void ceateTask() {
		String linkText = "";
		Dimension dimension = new Dimension(540 + 16, 865 + 93);
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
		Boolean addFlag = true;
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
		dimension = new Dimension(540 + 16, 865 + 93);
		String id = "create_task";
		linkText = "创建任务";
		map.putAll(createTaskService.enterToPageById(driver, dimension, id,
						linkText));

		taskDescription = "UPET-统一训战6月版本-V2R02I02";
		acceptanceStandard = "华为验收标准";
		// String query= "UPET-统一训战6月版本";
		projectName = "UPET-统一训战6月版本-V2R02I02";
		queryStringContactsName = "1691";
		contactsName = "[B-16916]邵京国";
		queryStringAcceptanceName = "邵京";
		acceptanceName = "[B-16916]邵京国";
		email = "abc@qq.com";
		phone = "13999999999";
		addFlag = true;
		map.putAll(createTaskService.createAndUpdateTaskByParams(driver,
						taskName, taskDescription, queryString, projectName,
						acceptanceStandard, queryStringContactsName,
						contactsName, queryStringAcceptanceName, acceptanceName,
						email, phone, addFlag));

		// 自定义浏览器窗口大小(16,93)
		dimension = new Dimension(540 + 16, 865 + 93);
		linkText = "任务所需资源清单";
		map.putAll(createTaskService.enterToPageByLinkText(driver, dimension,
						linkText));

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
		linkText = "技能要求";
		map.putAll(createTaskService.enterToPageByLinkText(driver, dimension,
						linkText));

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
		linkText = "领域要求";
		map.putAll(createTaskService.enterToPageByLinkText(driver, dimension,
						linkText));

		// 修改领域信息后自动返回
		String domainName = "CRM";
		String experienceName = "部分精通";
		map.putAll(createTaskService.addDomain(driver, domainName,
						experienceName));

		experienceName = "领域专家";
		map.putAll(createTaskService.editDomain(driver, domainName,
						experienceName));
		map.putAll(createTaskService.deleteDomain(driver, domainName));

		String aSubject = "任务看板--创建任务";
		sendMail(map, aSubject);

		super.tearDown();
	}

	/**
	 * 
	 */
	public void myTask() {
		String linkText = "";
		Dimension dimension = new Dimension(540 + 16, 865 + 93);
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
		Boolean addFlag = true;
		logger.debug("ceateTask");
		super.setUp(baseUrl);
		createTaskService = new CreateTaskService();
		Map<String, String> map = new LinkedHashMap<String, String>();
		String roleName = "PM";
		String staffName = "[B-16916]邵京国";

		// 登陆
		createTaskService.login(driver, roleName, staffName);

		// 任务看板--我发布的任务
		String id = "my_task";
		linkText = "我发布的任务";
		map.putAll(createTaskService.enterToPageById(driver, dimension, id,
						linkText));
		linkText = taskName;
		map.putAll(createTaskService.enterToPageBySpanText(driver, dimension,
						linkText));

		map.putAll(createTaskService.enterToPageBySpanText(driver, dimension,
						linkText));

		phone = "13999999999";
		addFlag = false;
		map.putAll(createTaskService.createAndUpdateTaskByParams(driver,
						taskName, taskDescription, queryString, projectName,
						acceptanceStandard, queryStringContactsName,
						contactsName, queryStringAcceptanceName, acceptanceName,
						email, phone, addFlag));

		String aSubject = "任务看板--创建任务";
		sendMail(map, aSubject);

		super.tearDown();
	}

	/**
	 * 
	 */
	public void searchTaskByWorkplace() {
		String linkText = "";
		Dimension dimension = new Dimension(540 + 16, 865 + 93);
		logger.debug("ceateTask");
		super.setUp(baseUrl);
		createTaskService = new CreateTaskService();
		Map<String, String> map = new LinkedHashMap<String, String>();
		String roleName = "PM";
		String staffName = "[B-16916]邵京国";

		// 登陆
		createTaskService.login(driver, roleName, staffName);

		// 任务看板--按工作城市查询任务
		String id = "search_task_by_workplace";
		linkText = "任务看板";
		map.putAll(createTaskService.enterToPageById(driver, dimension, id,
						linkText));

		searchTaskService = new SearchTaskService();
		String workPlace = "武汉";
		map.putAll(searchTaskService.searchTaskByWorkPlace(driver, workPlace));

		// 修改技能信息后自动返回 【任务所需资源清单】
		// String skillName = "iOS";
		// map.putAll(viewCreateTaskService.editSkill(driver, skillName));

		String aSubject = "按工作城市查询任务";
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
		new CreateTaskServiceJob().ceateTask();

		jvmPid();
		new CreateTaskServiceJob().myTask();

		jvmPid();
		new CreateTaskServiceJob().searchTaskByWorkplace();

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
