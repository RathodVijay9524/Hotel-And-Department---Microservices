package com.vijay.departmentservice.service;


import com.vijay.departmentservice.client.Employee;
import com.vijay.departmentservice.client.EmployeeClient;
import com.vijay.departmentservice.dto.DepartmentDto;
import com.vijay.departmentservice.dto.DepartmentResponse;
import com.vijay.departmentservice.entity.Department;
import com.vijay.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    private EmployeeClient employeeClient;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // convert department dto to department jpa entity
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()


        );

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()

        );

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        List<Employee> employeesByDept= employeeClient.getEmployeesByDept(department.getDepartmentCode());
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );

        return departmentDto;
    }

    @Override
    public List<Department> getDepartments() {

        return departmentRepository.findAll();
    }

//    @Override
//    public List<DepartmentResponse> getDepartmentWithEmployeList() {
//        List<Department> departments = departmentRepository.findAll();
//        departments.forEach(department -> {
//            department.setEmployeeList(employeeClient.getEmployeesByDept(department.getDepartmentCode()));
//        });
//        DepartmentResponse departmentResponse=new DepartmentResponse();
//        BeanUtils.copyProperties(departments, departmentResponse);
//        return (List<DepartmentResponse>) departmentResponse;
//    }


}
