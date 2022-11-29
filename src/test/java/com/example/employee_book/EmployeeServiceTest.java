package com.example.employee_book;

import com.example.employee_book.model.Employee;
import com.example.employee_book.record.EmployeeRequest;
import com.example.employee_book.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    private List<Employee> actualEmployeeList;

    @BeforeEach
    public void setup() {
        Employee employee1 = new Employee("Егор", "Пешков", 1, 100000);
        Employee employee2 = new Employee("Марина", "Круглова", 2, 120000);
        Employee employee3 = new Employee("Андрей", "Веселов", 1, 150000);
        actualEmployeeList = new ArrayList<>();
        actualEmployeeList.add(employee1);
        actualEmployeeList.add(employee2);
        actualEmployeeList.add(employee3);

        when(employeeService.getEmployees()).thenReturn(actualEmployeeList);
    }

    @Test
    public void getEmployeesTest() {
        final List<Employee> actual = actualEmployeeList;
        final List<Employee> expected = employeeService.getEmployees().stream().toList();
        assertEquals(expected, actual);
    }

    @Test
    public void addEmployeeTest() {
        Employee newEmployee = new Employee("Сергей", "Виноградов", 2, 110000);

        EmployeeRequest newEmployeeRequest = new EmployeeRequest();
        newEmployeeRequest.setFirstName(newEmployee.getFirstName());
        newEmployeeRequest.setLastName(newEmployee.getLastName());
        newEmployeeRequest.setDepartment(newEmployee.getDepartment());
        newEmployeeRequest.setSalary(newEmployee.getSalary());

        final Employee actual = newEmployee;
        final Employee expected = employeeService.addEmployee(newEmployeeRequest);
        assertEquals(expected, actual);
    }

    @Test
    public void checkSumSalary() {
        final int actual = actualEmployeeList.stream().mapToInt(Employee::getSalary).sum();
        final int expected = employeeService.getSumSalary();
        assertEquals(expected, actual);
    }

    @Test
    public void checkEmployeeWithMinSalary() {
        int idEmployeeWithMinSalary = 0;

        for (Employee employee : actualEmployeeList) {
            if (employee.getSalary() < actualEmployeeList.get(idEmployeeWithMinSalary).getSalary()) {
                idEmployeeWithMinSalary = employee.getId();
            }
        }

        final Employee actual = actualEmployeeList.get(idEmployeeWithMinSalary);
        final Employee expected = employeeService.getEmployeeWithMinSalary();
        assertEquals(expected, actual);
    }

    @Test
    public void getEmployeeWithMaxSalary() {
        int idEmployeeWithMaxSalary = 0;

        for (Employee employee : actualEmployeeList) {
            if (employee.getSalary() > actualEmployeeList.get(idEmployeeWithMaxSalary).getSalary()) {
                idEmployeeWithMaxSalary = employee.getId();
            }
        }

        final Employee actual = actualEmployeeList.get(idEmployeeWithMaxSalary);
        final Employee expected = employeeService.getEmployeeWithMaxSalary();
        assertEquals(expected, actual);
    }

    @Test
    public void getEmployeesWithSalaryMoreAverage() {
        Set<Employee> actual = new HashSet<>();
        double averageSalary = actualEmployeeList.stream().mapToInt(Employee::getSalary).sum() / actualEmployeeList.size();

        for (Employee employee : actualEmployeeList) {
            if (employee.getSalary() > averageSalary) {
                actual.add(employee);
            }
        }

        final Set<Employee> expected = employeeService.getEmployeesWithSalaryMoreAverage();
        assertEquals(expected, actual);
    }

}
