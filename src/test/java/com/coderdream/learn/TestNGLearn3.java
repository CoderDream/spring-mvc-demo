package com.coderdream.learn;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * http://blog.csdn.net/guoguo527/article/details/52414100
 * 使用priority指定执行顺序(默认值为0)，数值越小，越靠前执行
 * http://blog.csdn.net/d6619309/article/details/52755578
 * TestNG-详解preserve-order的作用与测试case的执行顺序
 */
public class TestNGLearn3 {
	@BeforeClass
	public void beforeClass() {
		System.out.println("this is before class");
	}

	@Test
	public void TestNgLearn() {
		System.out.println("this is TestNG test case");
	}

	@Test
	public void testMethod1() {
		System.out.println("this is testMethod1 test case： priority = 10");
	}

	@Test
	public void testMethod2() {
		System.out.println("this is testMethod2 test case： priority = 13");
	}

	@Test
	public void testMethod3() {
		System.out.println("this is testMethod3 test case： priority = 12");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("this is after class");
	}
}
