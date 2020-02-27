package com.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.main.model.Customer;
import com.main.model.Department;
import com.main.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${customer.role}")
	private String key;

	@SuppressWarnings("unchecked")
	public List<Department> getDepartments() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://Department-service/Departments",
				List.class);
		return (List<Department>) responseEntity.getBody();

	}

	// Select, Insert, Delete, Update Operations for an Customer

	@RequestMapping(value = "/Customer/{id}", method = RequestMethod.GET)
	Customer getCustomer(@PathVariable Integer id) {
		Customer c = customerService.findById(id).get();
		return c;
	}

	@RequestMapping(value = "/Customer", method = RequestMethod.POST)
	String addCustomer(@RequestBody Customer Customer) {
		Customer savedCustomer = customerService.save(Customer);
		return "SUCCESS" + savedCustomer.getId();
	}

	@RequestMapping(value = "/Customer", method = RequestMethod.PUT)
	Customer updateCustomer(@RequestBody Customer Customer) {
		Customer updatedCustomer = customerService.save(Customer);
		return updatedCustomer;
	}

	@RequestMapping(value = "/Customer", method = RequestMethod.DELETE)
	Map<String, String> deleteCustomer(@RequestParam Integer id) {
		Map<String, String> status = new HashMap<>();
		Optional<Customer> Customer = customerService.findById(id);
		if (Customer.isPresent()) {
			customerService.delete(Customer.get());
			status.put("Status", "Customer deleted successfully");
		} else {
			status.put("Status", "Customer not exist");
		}
		return status;
	}

	// Select, Insert, Delete for List of Customers

	@RequestMapping(value = "/Customers", method = RequestMethod.GET)
	List<Customer> getAllCustomer() {
		return customerService.findAll();
	}

	@RequestMapping(value = "/Customers", method = RequestMethod.POST)
	String addAllCustomers(@RequestBody List<Customer> CustomerList) {
		customerService.saveAll(CustomerList);
		return "SUCCESS";
	}

	@RequestMapping(value = "/Customers", method = RequestMethod.DELETE)
	String addAllCustomers() {
		customerService.deleteAll();
		return "SUCCESS";
	}

	@RequestMapping(value = "/Customers/Department/{id}", method = RequestMethod.GET)
	Object getAllDepartment(@PathVariable Integer id) {
		Customer customer = customerService.findById(id).get();
		if (customer.getRole().toLowerCase().equals(key.toLowerCase())) {
			return getDepartments();
		}

		return HttpStatus.FORBIDDEN;
	}
}
