package com.example.employee_book.service;

import com.example.employee_book.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesFromDepartment(int departmentId) {
        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public int getSumSalaryOfDepartment(int departmentId) {
        int sumSalary = 0;
        for (Employee employee : employeeService.getEmployees()) {
            if (employee.getDepartment() == departmentId) {
                sumSalary = sumSalary + employee.getSalary();
            }
        }
        return sumSalary;
    }

    public int getMaxSalaryOfDepartment(int departmentId) {
        int maxSalary = 0;
        for (Employee employee : employeeService.getEmployees()) {
            if (employee.getDepartment() == departmentId && employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
            }
        }
        return maxSalary;
    }

    public int getMinSalaryOfDepartment(int departmentId) {
        int minSalary = Integer.MAX_VALUE;
        for (Employee employee : employeeService.getEmployees()) {
            if (employee.getDepartment() == departmentId && employee.getSalary() < minSalary) {
                minSalary = employee.getSalary();
            }
        }
        return minSalary;
    }

    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        Map<Integer, List<Employee>> employeesByDepartment = new HashMap<>();
        for (Employee employee : employeeService.getEmployees()) {
            if (!employeesByDepartment.containsKey(employee.getDepartment())) {
                employeesByDepartment.put(employee.getDepartment(), getEmployeesFromDepartment(employee.getDepartment()));
            }
        }
        return employeesByDepartment;
    }

}
