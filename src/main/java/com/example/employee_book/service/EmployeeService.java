package com.example.employee_book.service;

import com.example.employee_book.model.Employee;
import com.example.employee_book.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Ошибка: имя введено некорректно");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSumSalary() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();
    }

    public Employee getEmployeeWithMinSalary() {
        int idEmployeeWithMinSalary = 0;

        for (Employee employee : employees.values()) {
            if (employee.getSalary() < employees.get(idEmployeeWithMinSalary).getSalary()) {
                idEmployeeWithMinSalary = employee.getId();
            }
        }

        return employees.get(idEmployeeWithMinSalary);
    }

    public Employee getEmployeeWithMaxSalary() {
        int idEmployeeWithMaxSalary = 0;

        for (Employee employee : employees.values()) {
            if (employee.getSalary() > employees.get(idEmployeeWithMaxSalary).getSalary()) {
                idEmployeeWithMaxSalary = employee.getId();
            }
        }

        return employees.get(idEmployeeWithMaxSalary);
    }

    public Set<Employee> getEmployeesWithSalaryMoreAverage() {
        Set<Employee> employeeWithLargeSalary = new HashSet<>();
        double averageSalary = employees.values().stream().mapToInt(Employee::getSalary).sum() / employees.size();

        for (Employee employee : employees.values()) {
            if (employee.getSalary() > averageSalary) {
                employeeWithLargeSalary.add(employee);
            }
        }
        return employeeWithLargeSalary;
    }
}

