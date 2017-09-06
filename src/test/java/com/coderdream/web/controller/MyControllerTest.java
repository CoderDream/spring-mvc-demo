package com.coderdream.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;

public class MyControllerTest {

	private static Logger logger = LoggerFactory
					.getLogger(MyControllerTest.class);

	String BASE_URL = "http://localhost:8088/spring-mvc-demo/";

	@Test
	public void hello() {
		String username = "admin";
		String password = "123456";
		RestTemplate restTemplate = new RestTemplate();
		String url = UriComponentsBuilder
						.fromHttpUrl(BASE_URL + "login?username=" + username
										+ "&password=" + password)
						.build().toUriString();
		logger.debug("url {} ", url);
		ResponseEntity<?> responseEntity = restTemplate.getForEntity(url,
						String.class);

		String result = (String) responseEntity.getBody();
		logger.debug("result {} ", result);
		// ObjectMapper mapper = new ObjectMapper();
		// String result = null;
		// try {
		// result = mapper.readValue(o, String.class);
		// logger.debug("result {} ", result);
		// } catch (JsonParseException e) {
		// e.printStackTrace();
		// } catch (JsonMappingException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}