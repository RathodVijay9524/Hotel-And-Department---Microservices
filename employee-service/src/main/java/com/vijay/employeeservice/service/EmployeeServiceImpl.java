package com.vijay.employeeservice.service;

import com.vijay.employeeservice.dto.APIResponseDto;
import com.vijay.employeeservice.dto.DepartmentDto;
import com.vijay.employeeservice.dto.EmployeeDto;
import com.vijay.employeeservice.entity.Employee;
import com.vijay.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee saveDEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                saveDEmployee.getId(),
                saveDEmployee.getFirstName(),
                saveDEmployee.getLastName(),
                saveDEmployee.getEmail(),
                saveDEmployee.getDepartmentCode()
        );

        return savedEmployeeDto;

    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        // using restTemplate

        ResponseEntity<DepartmentDto> forEntity =
                restTemplate.getForEntity("http://localhost:8085/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto1 = forEntity.getBody();

        // way 2--- using webclient -----

        DepartmentDto departmentDto2 = webClient.get()
                .uri("http://localhost:8085/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        // way 3 --- using Fain-client -----


        DepartmentDto departmentDto3= apiClient.getDepartment(employee.getDepartmentCode());


        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto3);

        return apiResponseDto;

    }

    @Override
    public List<Employee> getEmployeeByDepartmentCode(String departmentCode) {
        List<Employee> byDepartmentCode = employeeRepository.findByDepartmentCode(departmentCode);
        return byDepartmentCode;
    }
}
