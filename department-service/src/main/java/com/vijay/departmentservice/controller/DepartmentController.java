package com.vijay.departmentservice.controller;

import com.vijay.departmentservice.dto.DepartmentDto;
import com.vijay.departmentservice.entity.Department;
import com.vijay.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    // Build save department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    // Build get department rest api
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

//    @GetMapping("/with-employee")
//    public ResponseEntity<List<Department>> getAllDepartmentWithEmp(){
//        List<Department> departments = departmentService.getDepartmentWithEmployeList();
//        return new ResponseEntity<>(departments, HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        List<Department> departments = departmentService.getDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}