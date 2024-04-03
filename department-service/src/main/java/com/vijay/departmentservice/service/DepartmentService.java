package com.vijay.departmentservice.service;

import com.vijay.departmentservice.dto.DepartmentDto;
import com.vijay.departmentservice.dto.DepartmentResponse;
import com.vijay.departmentservice.entity.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);

    List<Department> getDepartments();

   //List<DepartmentResponse> getDepartmentWithEmployeList();
}
