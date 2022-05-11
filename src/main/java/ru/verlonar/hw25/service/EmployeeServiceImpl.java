package ru.verlonar.hw25.service;

import org.springframework.stereotype.Service;
import ru.verlonar.hw25.data.Employee;
import ru.verlonar.hw25.exception.EmployeeIsAlreadyExistException;
import ru.verlonar.hw25.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final List<Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = contains(firstName, lastName);
        if (newEmployee != null) {
            throw new EmployeeIsAlreadyExistException();
        } else {
            newEmployee = new Employee(firstName, lastName);
            employees.add(newEmployee);
            return newEmployee;
        }
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employeeToDelete = contains(firstName, lastName);
        if (employeeToDelete == null) {
            throw new EmployeeNotFoundException();
        } else {
            employees.remove(employeeToDelete);
            return employeeToDelete;
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employeeSoughtFor = contains(firstName, lastName);
        if (employeeSoughtFor == null) {
            throw new EmployeeNotFoundException();
        } else {
            return employeeSoughtFor;
        }
    }

    @Override
    public List<Employee> showAll() {
        return employees;
    }

    private Employee contains(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().contains(lastName)) {
                return employee;
            }
        }
        return null;
    }
}