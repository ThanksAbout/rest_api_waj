package sk.ukf.uloha2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest_api")
class EmployeeRestController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if( employee == null) {
            throw new RuntimeException(HttpStatus.NOT_FOUND + "Employee not found" + id);
        }
        return employee;
    }
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(null);
        Employee savedEmployee = employeeService.save(employee);
        return savedEmployee;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            throw new RuntimeException(HttpStatus.NOT_FOUND + "Employee not found" + id);
        }
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, String> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);

        if (employee != null) {
            employeeService.delete(id);
            return Map.of("message", "Employee deleted successfully");
        }
        throw new RuntimeException(HttpStatus.NOT_FOUND + "Employee not found" + id);
    }


}
