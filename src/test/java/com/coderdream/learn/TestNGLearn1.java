package com.coderdream.learn;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * http://blog.csdn.net/guoguo527/article/details/52414100
 *
 */
public class TestNGLearn1 {
	@BeforeClass
	public void beforeClass() {
		System.out.println("this is before class");
	}

	@Test
	public void TestNgLearn() {
		System.out.println("this is TestNG test case");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("this is after class");
	}
}
