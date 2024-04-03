package com.vijay.employeeservice.service;

import com.vijay.employeeservice.dto.APIResponseDto;
import com.vijay.employeeservice.dto.EmployeeDto;
import com.vijay.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);

    List<Employee> getEmployeeByDepartmentCode(String departmentCode);
}

