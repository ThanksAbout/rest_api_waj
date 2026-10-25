package sk.ukf.uloha2.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.ukf.uloha2.dto.ApiResponse;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.exception.ResourceNotFoundException;
import sk.ukf.uloha2.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/rest_api")
class EmployeeRestController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ApiResponse<List<Employee>> getEmployees() {
        return ApiResponse.success(employeeService.findAll(), "Employees retrieved successfully");
    }

    @GetMapping("/employees/{id}")
    public ApiResponse<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found: " + id);
        }
        return ApiResponse.success(employee, "Employee retrieved successfully");
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(null);
        Employee savedEmployee = employeeService.save(employee);
        return ApiResponse.success(savedEmployee, "Employee created successfully");
    }

    @PutMapping("/employees/{id}")
    public ApiResponse<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            throw new ResourceNotFoundException("Employee not found: " + id);
        }
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return ApiResponse.success(updatedEmployee, "Employee updated successfully");
    }

    @DeleteMapping("/employees/{id}")
    public ApiResponse<Void> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found: " + id);
        }
        employeeService.delete(id);
        return ApiResponse.success(null, "Employee deleted successfully");
    }
}
