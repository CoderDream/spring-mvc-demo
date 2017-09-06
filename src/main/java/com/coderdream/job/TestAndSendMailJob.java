package com.coderdream.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.util.CommonsMailUtil;
import com.coderdream.util.Constants;

public class TestAndSendMailJob {

	private static Logger logger = LoggerFactory
					.getLogger(TestAndSendMailJob.class);

	public void testAndSendMail() {
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(
						Constants.COMPLEX_DATE_FORMAT2);
		logger.debug("testAndSendMailJob at: " + sf.format(today));

		String aSubject = "Test email with inline image";

		String fileName = "20170906115041194_luanchLogin.png";
		String imgUrl = System.getProperty("user.dir") + "\\snapshot\\"
						+ fileName;

		// set the html message
		String aHtml = "<html>The apache logo - <img src=\"" + imgUrl
						+ "\"></html>";
		String aText = "Your email client does not support HTML messages";

		Map<String, String> addressMap = null;
		CommonsMailUtil.sendingHtmlFormattedEmail(aSubject, aHtml, aText,
						addressMap);
	}

	public static void main(String[] args) {
		new TestAndSendMailJob().testAndSendMail();
	}
}
