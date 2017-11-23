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
import com.coderdream.selenium.service.sadp.MartCodeService;

public class MartCodeServiceJob extends BaseJob {

	private static Logger logger = LoggerFactory
					.getLogger(MartCodeServiceJob.class);

	private MartCodeService viewMartCodeService;

	private String baseUrl = "marturl";
	
	public void getMyProfile() {
		logger.debug("getMyProfile");
		super.setUp(baseUrl);
		viewMartCodeService = new MartCodeService();
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		String roleName = "普通员工";
//		String staffName = "[B-30728]吕帅";

		// 登陆
		viewMartCodeService.index(driver);
//
//		// 进入【我的人力看板】页面
//		String linkText = "我的人力档案";
//		Dimension dimension =  new Dimension(540 + 16, 865 + 93);
//		map.putAll(viewMartCodeService.enterToPageByLinkText(driver, dimension,
//						linkText));
//
//		// 进入【我的基本信息】页面
//		map.putAll(viewMartCodeService.myProfileBaseInfo(driver));
//
//		// 修改基本信息后自动返回
//		map.putAll(viewMartCodeService.updateMyProfileBaseInfo(driver));
//
//		// 修改技能信息后自动返回
//		// String skillName = "iOS";
//		// map.putAll(viewMartCodeService.editSkill(driver, skillName));
//
//		String aSubject = "我的人力档案";
//		sendMail(map, aSubject);

		super.tearDown();
	}


	public static void main(String[] args) {
		new MartCodeServiceJob().getMyProfile();
		//new MartCodeServiceJob().operateProfileSkillInfo();

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
