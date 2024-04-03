package com.vijay.employeeservice.repository;

import com.vijay.employeeservice.dto.EmployeeDto;
import com.vijay.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByDepartmentCode(String departmentCode);
}
