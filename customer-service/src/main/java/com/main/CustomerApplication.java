package com.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.main.model.Customer;
import com.main.service.CustomerService;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService cust;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {


		Customer c = new Customer();
		c.setName("john");
		c.setAddress("Gurgaon");
		c.setMob("123456789");
		c.setRole("Admin");
		Customer c1 = new Customer();
		c1.setName("Manihs");
		c1.setAddress("Gurgaon");
		c1.setMob("123456789");
		c1.setRole("customer");

		logger.info("Inserting -> {}", cust.save(c));
		logger.info("Inserting -> {}", cust.save(c1));

		c.setName("Ajay");

		logger.info("Update 10003 -> {}", cust.save(c));

		cust.deleteById(2);

		logger.info("All users -> {}", cust.findAll());
	}

	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
