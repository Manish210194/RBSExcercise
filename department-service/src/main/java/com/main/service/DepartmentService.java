package com.main.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.model.Department;
@Repository
public interface DepartmentService extends JpaRepository<Department, Integer>{

}

