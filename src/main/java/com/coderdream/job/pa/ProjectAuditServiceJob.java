package com.coderdream.job.pa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.job.BaseJob;
import com.coderdream.selenium.service.sadp.ProjectAuditService;

public class ProjectAuditServiceJob extends BaseJob {

	private static Logger logger = LoggerFactory
					.getLogger(ProjectAuditServiceJob.class);

	private ProjectAuditService viewProjectAuditService;

	private String baseUrl = "auditurl";

	public void getProjectAudit() {
		logger.debug("getProjectAudit");
		super.setUp(baseUrl);
		viewProjectAuditService = new ProjectAuditService();
		Map<String, String> map = new LinkedHashMap<String, String>();
		String roleName = "QA|PMO|运营";
		String staffName = "[B-7382]全荃";

		// 登陆
		viewProjectAuditService.login(driver, roleName, staffName);

		// 进入【我的人力看板】页面
		map.putAll(viewProjectAuditService.newAudit(driver));

		// 进入【我的基本信息】页面

		String projectName = "美的-美的开利相关软件测试项目5月版本";
		map.putAll(viewProjectAuditService.queryProject(driver, projectName));

		map.putAll(viewProjectAuditService.addAudit(driver));

		//
		String auditName = "变更修正合理性";
		// String auditTypeKey = "RO01";
		String auditContent = "评价内容";
		Integer auditState = 0;
		// 修改基本信息后自动返回
		map.putAll(viewProjectAuditService.saveAudit(driver, auditName,
						auditContent, auditState));

//		String auditContentNew = "评价内容";
//		Integer auditStateNew = 1;
//		map.putAll(viewProjectAuditService.updateAudit(driver, auditName,
//						auditContentNew, auditStateNew, staffName));

		// 修改技能信息后自动返回
		// String skillName = "iOS";
		// map.putAll(viewProjectAuditService.editSkill(driver, skillName));

		String aSubject = "自动化测试--查看我的人力档案";
		sendMail(map, aSubject);

		super.tearDown();
	}

	public void operateProfileSkillInfo() {
		logger.debug("getProjectAudit");
		super.setUp(baseUrl);
		viewProjectAuditService = new ProjectAuditService();
		Map<String, String> map = new LinkedHashMap<String, String>();
		String roleName = "QA|PMO|运营";
		String staffName = "[B-7382]全荃";

		// 登陆
		viewProjectAuditService.login(driver, roleName, staffName);

		// 进入【我的人力看板】页面
		map.putAll(viewProjectAuditService.newAudit(driver));

		// 修改技能信息后自动返回
		String skillName = "iOS";
		String proficiencyName = "熟练";
		map.putAll(viewProjectAuditService.addSkill(driver, skillName,
						proficiencyName));

		proficiencyName = "精通";
		map.putAll(viewProjectAuditService.editSkill(driver, skillName,
						proficiencyName));

		map.putAll(viewProjectAuditService.deleteSkill(driver, skillName));

		String aSubject = "自动化测试--修改人力档案的技能信息";
		sendMail(map, aSubject);

		super.tearDown();
	}

	public static void main(String[] args) {
		new ProjectAuditServiceJob().getProjectAudit();
		// new ProjectAuditServiceJob().operateProfileSkillInfo();

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
