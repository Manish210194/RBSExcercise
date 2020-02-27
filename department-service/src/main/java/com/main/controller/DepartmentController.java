package com.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Department;
import com.main.service.DepartmentService;


@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	// Select, Insert, Delete, Update Operations for an Department

	@GetMapping("/department/{id}")
	Department getDepartment(@PathVariable Integer id) {
		return departmentService.findById(id).get();
	}

	@RequestMapping(value = "/Department", method = RequestMethod.POST)
	String addDepartment(@RequestBody Department department) {
		System.out.println("manish");
		Department savedDepartment = departmentService.save(department);
		return "SUCCESS" +savedDepartment.getId();
	}

	@RequestMapping(value = "/Department", method = RequestMethod.PUT)
	Department updateDepartment(@RequestBody Department department) {
		Department updatedDepartment = departmentService.save(department);
		return updatedDepartment;
	}

	@RequestMapping(value = "/Department", method = RequestMethod.DELETE)
	Map<String, String> deleteDepartment(@RequestParam Integer id) {
		Map<String, String> status = new HashMap<>();
		Optional<Department> Department = departmentService.findById(id);
		if (Department.isPresent()) {
			departmentService.delete(Department.get());
			status.put("Status", "Department deleted successfully");
		} else {
			status.put("Status", "Department not exist");
		}
		return status;
	}

	// Select, Insert, Delete for List of Departments
	
	@RequestMapping(value = "/Departments", method = RequestMethod.GET)
	List<Department> getAllDepartment() {
		return departmentService.findAll();
	}

	@RequestMapping(value = "/Departments", method = RequestMethod.POST)
	String addAllDepartments(@RequestBody List<Department> DepartmentList) {
		departmentService.saveAll(DepartmentList);
		return "SUCCESS";
	}

	@RequestMapping(value = "/Departments", method = RequestMethod.DELETE)
	String addAllDepartments() {
		departmentService.deleteAll();
		return "SUCCESS";
	}
}