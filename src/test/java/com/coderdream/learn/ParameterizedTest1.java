package com.coderdream.learn;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizedTest1 {

	@Test
	@Parameters("test1")
	public void ParaTest(String test1) {
		System.out.println("This is " + test1);
	}
}
