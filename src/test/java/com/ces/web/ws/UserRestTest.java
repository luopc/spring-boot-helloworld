package com.ces.web.ws;

import com.ws.Application;
import com.ws.util.HttpClientTool;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


public class UserRestTest {

	@Before
	public void setUp() {
		SpringApplication.run(Application.class);
	}

	@Test
	public void  test(){
		String data = HttpClientTool.get("http://localhost:8080/user/1");
		System.out.println(data);
	}

}
