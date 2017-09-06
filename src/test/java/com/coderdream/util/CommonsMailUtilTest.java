package com.coderdream.util;

import java.util.Map;

import org.testng.annotations.Test;

public class CommonsMailUtilTest {

	@Test
	public void testSendSimpleTextEmail() {
		CommonsMailUtil.sendSimpleTextEmail();
	}

	@Test
	public void testSendingEmailsWithAttachments() {
		CommonsMailUtil.sendingEmailsWithAttachments();
	}

	@Test
	public void testSendingEmailsWithAttachmentsUseURL() {
		CommonsMailUtil.sendingEmailsWithAttachmentsUseURL();
	}

	@Test
	public void testSendingHtmlFormattedEmail() {
		CommonsMailUtil.sendingHtmlFormattedEmail();
	}

	@Test
	public void testSendingHtmlFormattedEmail_02() {
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

	@Test
	public void testSendingHtmlFormattedEmailWithEmbeddedImages() {
		CommonsMailUtil.sendingHtmlFormattedEmailWithEmbeddedImages();
	}

	@Test
	public void testSendHtmlEmailWithImg() {
		CommonsMailUtil.sendHtmlEmailWithImg();
	}

}
