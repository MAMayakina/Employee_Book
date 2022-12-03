package com.example.employee_book;

import com.example.employee_book.model.Employee;
import com.example.employee_book.record.EmployeeRequest;
import com.example.employee_book.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private List<EmployeeRequest> requests;

    @BeforeEach
    public void setup() {
        employeeService = new EmployeeService();
        this.requests = new ArrayList<>();

        EmployeeRequest employee1 = createEmployeeRequest("Егор", "Пешков", 1, 100000);
        EmployeeRequest employee2 = createEmployeeRequest("Марина", "Круглова", 2, 120000);
        EmployeeRequest employee3 = createEmployeeRequest("Андрей", "Веселов", 1, 150000);

        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);

        requests.add(employee1);
        requests.add(employee2);
        requests.add(employee3);
    }

    private EmployeeRequest createEmployeeRequest(String firstName, String lastName, int department, int salary) {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setFirstName(firstName);
        employeeRequest.setLastName(lastName);
        employeeRequest.setDepartment(department);
        employeeRequest.setSalary(salary);
        return employeeRequest;
    }

    @Test
    public void getEmployeesTest() {
        final Collection<Employee> actual = employeeService.getEmployees();
        assertEquals(3, actual.size());

        for (Employee employee : actual) {
            for (EmployeeRequest request : requests) {
                if (request.getFirstName().equals(employee.getFirstName()) && request.getLastName().equals(employee.getLastName())) {
                    assertEquals(request.getFirstName(), employee.getFirstName());
                    assertEquals(request.getLastName(), employee.getLastName());
                    assertEquals(request.getDepartment(), employee.getDepartment());
                    assertEquals(request.getSalary(), employee.getSalary());
                }
            }
        }

    }

    @Test
    public void addEmployeeTest() {
        Employee newEmployee = new Employee("Сергей", "Виноградов", 2, 110000);

        EmployeeRequest newEmployeeRequest = new EmployeeRequest();
        newEmployeeRequest.setFirstName(newEmployee.getFirstName());
        newEmployeeRequest.setLastName(newEmployee.getLastName());
        newEmployeeRequest.setDepartment(newEmployee.getDepartment());
        newEmployeeRequest.setSalary(newEmployee.getSalary());

        final Employee actual = employeeService.addEmployee(newEmployeeRequest);
        final EmployeeRequest expected = newEmployeeRequest;

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getSalary(), actual.getSalary());

    }

    @Test
    public void checkSumSalary() {
        int actual = employeeService.getSumSalary();
        int expected = 370000;
        assertEquals(expected, actual);
    }

    @Test
    public void checkEmployeeWithMinSalary() {
        final Employee actual = employeeService.getEmployeeWithMinSalary();
        EmployeeRequest expected = new EmployeeRequest();

        int minSalary = 100000;
        for (EmployeeRequest request : requests) {
            if (request.getSalary() == minSalary) {
                expected = request;
            }
        }

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getSalary(), actual.getSalary());

    }

    @Test
    public void checkEmployeeWithMaxSalary() {
        final Employee actual = employeeService.getEmployeeWithMaxSalary();
        EmployeeRequest expected = new EmployeeRequest();

        int maxSalary = 150000;
        for (EmployeeRequest request : requests) {
            if (request.getSalary() == maxSalary) {
                expected = request;
            }
        }

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getSalary(), actual.getSalary());
    }

    @Test
    public void checkEmployeeWithSalaryMoreAverage() {
        Set<EmployeeRequest> expected = new HashSet<>();
        double averageSalary = requests.stream().mapToInt(EmployeeRequest::getSalary).sum() / requests.size();
        for (EmployeeRequest request : requests) {
            if (request.getSalary() > averageSalary) {
                expected.add(request);
            }
        }

        final Set<Employee> actual = employeeService.getEmployeesWithSalaryMoreAverage();

        assertEquals(expected.size(), actual.size());

        for (Employee employee : actual) {
            EmployeeRequest newEmployee = new EmployeeRequest();
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setDepartment(employee.getDepartment());
            newEmployee.setSalary(employee.getSalary());

            for (EmployeeRequest employeeRequest : expected) {
                if (employeeRequest.getFirstName().equals(newEmployee.getFirstName()) && employeeRequest.getLastName().equals(newEmployee.getLastName())) {
                    assertEquals(newEmployee.getFirstName(), employeeRequest.getFirstName());
                    assertEquals(newEmployee.getLastName(), employeeRequest.getLastName());
                    assertEquals(newEmployee.getDepartment(), employeeRequest.getDepartment());
                    assertEquals(newEmployee.getSalary(), employeeRequest.getSalary());
                }
            }
        }
    }
}


