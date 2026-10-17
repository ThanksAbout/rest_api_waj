package sk.ukf.uloha2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.uloha2.dao.EmployeeDAO;
import sk.ukf.uloha2.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl  implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
    @Override
    public Employee findById(Long id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (employeeDAO.findById(id) != null) {
            employeeDAO.delete(id);
        }
    }
}
