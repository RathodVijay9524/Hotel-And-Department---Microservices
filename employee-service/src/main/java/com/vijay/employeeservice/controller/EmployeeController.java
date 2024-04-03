package com.vijay.employeeservice.controller;

import com.vijay.employeeservice.dto.APIResponseDto;
import com.vijay.employeeservice.dto.EmployeeDto;
import com.vijay.employeeservice.entity.Employee;
import com.vijay.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> getEmployeesByDept(@PathVariable("id") String departmentCode){
        List<Employee> employeeByDepartmentCode= employeeService.getEmployeeByDepartmentCode(departmentCode);
        return new ResponseEntity<>(employeeByDepartmentCode, HttpStatus.OK);
    }
}
