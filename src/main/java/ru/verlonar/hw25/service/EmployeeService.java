package ru.verlonar.hw25.service;

import ru.verlonar.hw25.Employee;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);
}
