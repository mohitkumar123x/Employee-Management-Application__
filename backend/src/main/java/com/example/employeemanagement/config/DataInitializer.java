package com.example.employeemanagement.config;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** This class initializes fake data for the application when it starts. */
@Configuration
public class DataInitializer implements CommandLineRunner {

  /** The department repository. */
  @Autowired private DepartmentRepository departmentRepository;

  /** The employee repository. */
  @Autowired private EmployeeRepository employeeRepository;

  private final Faker faker = new Faker();
  private final Random random = new Random();

  /**
   * This method is called when the application starts.
   *
   * @param args Command line arguments
   */
  @Override
  public void run(String... args) {
    // Always clear existing data before inserting new data
    employeeRepository.deleteAll();
    departmentRepository.deleteAll();

    // Create mock departments
    List<Department> departments = new ArrayList<>();
    String[] deptNames = {"Engineering", "Product Management", "Design", "Marketing", "People Operations", "Sales", "Finance", "Customer Success", "Legal", "IT Support"};
    for (String name : deptNames) {
      Department department = new Department();
      department.setName(name);
      departments.add(department);
    }
    departmentRepository.saveAll(departments);

    // Create mock employees
    List<Employee> employees = new ArrayList<>();
    String[][] employeeData = {
      {"Ishaan", "Malhotra", "ishaan.malhotra@example.com", "28"},
      {"Meera", "Joshi", "meera.joshi@example.com", "33"},
      {"Dev", "Kulkarni", "dev.kulkarni@example.com", "37"},
      {"Riya", "Bansal", "riya.bansal@example.com", "25"},
      {"Karan", "Chopra", "karan.chopra@example.com", "41"},
      {"Aditi", "Saxena", "aditi.saxena@example.com", "30"},
      {"Nikhil", "Desai", "nikhil.desai@example.com", "39"},
      {"Pooja", "Menon", "pooja.menon@example.com", "32"},
      {"Sameer", "Arora", "sameer.arora@example.com", "35"},
      {"Tanvi", "Ghosh", "tanvi.ghosh@example.com", "29"}
    };

    for (String[] data : employeeData) {
      Employee employee = new Employee();
      employee.setFirstName(data[0]);
      employee.setLastName(data[1]);
      employee.setEmail(data[2]);
      employee.setAge(Integer.parseInt(data[3]));
      employee.setDepartment(departments.get(random.nextInt(departments.size())));
      employees.add(employee);
    }

    // Add some random employees to fill the list if needed
    for (int i = 1; i <= 40; i++) {
      Employee employee = new Employee();
      employee.setFirstName(faker.name().firstName());
      employee.setLastName(faker.name().lastName());
      employee.setEmail(faker.internet().emailAddress());
      employee.setAge(random.nextInt(40) + 20);
      employee.setDepartment(departments.get(random.nextInt(departments.size())));
      employees.add(employee);
    }
    employeeRepository.saveAll(employees);

    System.out.println("Fake data initialized successfully, replacing any existing data!");
  }
}
