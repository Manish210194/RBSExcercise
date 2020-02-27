package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.main.controller.CustomerController;

import antlr.collections.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CustomerController.class})
@ContextConfiguration(locations = {
"classpath*:spring/applicationContext.xml",
"classpath*:spring/applicationContext-jpa.xml"})
public class CustomerTests {
	@Test
	public void testService() {
		
		RestTemplate rest= new RestTemplate();
		
		ResponseEntity<List> responseEntity=(ResponseEntity<List>) rest.exchange("http://localhost:8085/Customers/Department/{id}", HttpMethod.GET,null,List.class,234156156).getBody();
		assertEquals(responseEntity.getBody().length(),0);
	}

}
