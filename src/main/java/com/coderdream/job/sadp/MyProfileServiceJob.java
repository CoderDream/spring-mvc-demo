package com.coderdream.job.sadp;

import java.util.LinkedHashMap;
import java.util.Map;

import com.coderdream.selenium.service.sadp.MyProfileService;

public class MyProfileServiceJob extends BaseJob {

	private MyProfileService viewMyProfileService;

	public void getMyProfile() {
		super.setUp();
		viewMyProfileService = new MyProfileService();
		Map<String, String> map = new LinkedHashMap<String, String>();
		String roleName = "普通员工";
		String staffName = "[B-21945]颜冰";

		// 登陆
		viewMyProfileService.login(driver, roleName, staffName);

		// 进入【我的人力看板】页面
		map.putAll(viewMyProfileService.myProfile(driver));

		// 进入【我的基本信息】页面
		map.putAll(viewMyProfileService.myProfileBaseInfo(driver));

		// 修改基本信息后自动返回
		//map.putAll(viewMyProfileService.updateMyProfileBaseInfo(driver));

		// 修改技能信息后自动返回
		//String skillName = "iOS";
		//map.putAll(viewMyProfileService.editSkill(driver, skillName));

		String aSubject = "自动化测试--查看我的人力档案";
		sendMail(map, aSubject);

		super.tearDown();
	}

	public static void main(String[] args) {
		new MyProfileServiceJob().getMyProfile();

		// logger.debug(System.getProperty("user.dir"));
	}
}
