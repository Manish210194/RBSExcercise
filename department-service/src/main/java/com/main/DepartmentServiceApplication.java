package com.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.main.model.Department;
import com.main.service.DepartmentService;

@SpringBootApplication
@EnableEurekaClient
public class DepartmentServiceApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepartmentService depart;

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {

		Department d = new Department();
		d.setName("Department");
		d.setItems("Cloth");
		d.setType("garment");
		Department d1 = new Department();
		d1.setName("Mother Diary");
		d.setItems("cow milk");
		d.setType("Diary");

		logger.info("Inserting -> {}", depart.save(d));
		logger.info("Inserting -> {}", depart.save(d1));

		d.setName("Ajay");

		logger.info("Update 10003 -> {}", depart.save(d));

		depart.deleteById(2);

		logger.info("All users -> {}", depart.findAll());
	}

}
