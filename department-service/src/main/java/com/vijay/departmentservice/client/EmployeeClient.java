package com.vijay.departmentservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("/api/employees/department/{id}")
    public List<Employee> getEmployeesByDept(@PathVariable("id") String departmentCode);
}
