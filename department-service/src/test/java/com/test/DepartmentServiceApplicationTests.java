package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.main.model.Department;
import com.main.service.DepartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DepartmentService.class,Department.class})
@ContextConfiguration(locations = {
"classpath*:spring/applicationContext.xml",
"classpath*:spring/applicationContext-jpa.xml"})
public class DepartmentServiceApplicationTests {

	
	@Autowired
	private DepartmentService department;
	@Test
	public void EntityTest() {
		Department d = new Department();
		d.setName("Department");
		d.setItems("Cloth");
		d.setType("garment");
		Department d1 = new Department();
		d1.setName("Mother Diary");
		d.setItems("cow milk");
		d.setType("Diary");

		assertEquals(1, department.save(d));
		assertEquals(1, department.save(d1));
		
	}
	
	

}

