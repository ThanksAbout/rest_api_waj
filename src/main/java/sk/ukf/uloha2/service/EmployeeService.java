package sk.ukf.uloha2.service;

import sk.ukf.uloha2.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee save(Employee employee);
    void delete(Long id);
}