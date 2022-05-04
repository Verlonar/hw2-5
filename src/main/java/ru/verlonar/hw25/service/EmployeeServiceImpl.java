package ru.verlonar.hw25.service;

import org.springframework.stereotype.Service;
import ru.verlonar.hw25.Employee;
import ru.verlonar.hw25.exception.EmployeeIsAlreadyExistException;
import ru.verlonar.hw25.exception.EmployeeNotFoundException;
import ru.verlonar.hw25.exception.EmployeesArrayIsFullException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final Employee[] employees;

    private int employeesNumber;

    public EmployeeServiceImpl() {
        this.employees = new Employee[10];
        employeesNumber = 0;
    }


    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employeesNumber >= employees.length) {
            throw new EmployeesArrayIsFullException();
        } else if (findEmployeeIndex(firstName, lastName) != -1) {
            throw new EmployeeIsAlreadyExistException();
        } else {
            int emptyIndex = findEmptyIndex();
            employees[emptyIndex] = new Employee(firstName, lastName);
            employeesNumber++;
            return employees[emptyIndex];
        }
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        int indexToDelete = findEmployeeIndex(firstName, lastName);
        if (indexToDelete == -1) {
            throw new EmployeeNotFoundException();
        } else {
            Employee employeeToDelete = employees[indexToDelete];
            employees[indexToDelete] = null;
            employeesNumber--;
            return employeeToDelete;
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        int employeeIndex = findEmployeeIndex(firstName, lastName);
        if (employeeIndex == -1) {
            throw new EmployeeNotFoundException();
        } else {
            return employees[employeeIndex];
        }
    }

    private int findEmployeeIndex(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && (employees[i].toString().equals(firstName + " " + lastName) )) {
                return i;
            }
        }
        return -1;
    }

    private int findEmptyIndex() {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                return i;
            }
        }
        return -1;
    }
}