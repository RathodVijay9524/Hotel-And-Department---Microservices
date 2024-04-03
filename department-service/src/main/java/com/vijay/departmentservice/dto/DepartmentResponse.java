package com.vijay.departmentservice.dto;

import com.vijay.departmentservice.client.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {

    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
    private List<Employee> EmployeeList=new ArrayList<>();
}
