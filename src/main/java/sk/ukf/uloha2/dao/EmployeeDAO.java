package sk.ukf.uloha2.dao;

import sk.ukf.uloha2.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findById(Long id);
    Employee save(Employee employee);
    void delete(Long id);
}
