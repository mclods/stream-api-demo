package org.mclods;

import org.mclods.db.EmployeeDB;
import org.mclods.entities.Employee;
import org.mclods.entities.Project;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeDB.findAllEmployees();

        // for each
        System.out.println("Find and print all employees using forEach");
        employees.forEach(System.out::println);
        System.out.println();


        System.out.println("Find and print all employees using streams");
        employees.stream().forEach(System.out::println);
        System.out.println();


        // filter
        System.out.println("Find employees who work in the development department");
        employees.stream().filter(e -> e.getDepartment().equals("Development")).forEach(System.out::println);
        System.out.println();


        // count
        System.out.println("Find count of employees who work in the development department");
        long count = employees.stream().filter(e -> e.getDepartment().equals("Development")).count();
        System.out.println(count);
        System.out.println();


        // filter with multiple conditions
        System.out.println("Find employees who work in the development department and whose salary is greater than 12000");
        employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .forEach(System.out::println);
        System.out.println();


        // filter and collect
        System.out.println("Return a list of employees who work in the development department");
        List<Employee> devEmployeesList = employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .collect(Collectors.toList());
        System.out.println(devEmployeesList);
        System.out.println();


        System.out.println("Return a set of employees who work in the development department");
        Set<Employee> devEmployeesSet = employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .collect(Collectors.toSet());
        System.out.println(devEmployeesSet);
        System.out.println();


        System.out.println("Return a map of employees (id as key and name as value) who work in the development department");
        Map<Integer, String> devEmployeesMap = employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .collect(Collectors.toMap(Employee::getId, Employee::getName));
        System.out.println(devEmployeesMap);
        System.out.println();


        // map
        System.out.println("Return a list of all employee ids");
        List<Integer> employeeIds = employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        System.out.println(employeeIds);
        System.out.println();


        System.out.println("Return a list of the all the departments");
        List<String> employeeDepartmentsList = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toList());
        System.out.println(employeeDepartmentsList);
        System.out.println();


        System.out.println("Return a set of unique departments");
        Set<String> employeeDepartmentsSet = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
        System.out.println(employeeDepartmentsSet);
        System.out.println();


        System.out.println("Return a list of unique departments");
        List<String> employeeDepartmentsListNoDuplicates = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(employeeDepartmentsListNoDuplicates);
        System.out.println();


        System.out.println("Find the list of all the projects that employees work on (ONE TO MANY RELATIONSHIP)");
        List<List<String>> projectsListofList = employees.stream()
                .map(e -> e.getProjects().stream()
                        .map(Project::getName).collect(Collectors.toList())
                ).collect(Collectors.toList());
        System.out.println(projectsListofList);
        System.out.println();
        // This is List<List<String>> to obtain a List<String> we need to use a flatMap


        // flat map
        System.out.println("Find the list of all the projects that employees work on (ONE TO MANY RELATIONSHIP)");
        List<String> projectsList = employees.stream()
                .flatMap(e -> e.getProjects().stream())
                .map(Project::getName)
                .collect(Collectors.toList());
        System.out.println(projectsList);
        System.out.println();
        // This list contains duplicates


        System.out.println("Find the list of all the projects (no duplicates) that employees work on (ONE TO MANY RELATIONSHIP)");
        List<String> distinctprojectsList = employees.stream()
                .flatMap(e -> e.getProjects().stream())
                .map(Project::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctprojectsList);
        System.out.println();


        // sorted asc
        System.out.println("Find list of all employees sorted based on their salaries");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);
        System.out.println();
        // This is sorting in ascending order


        System.out.println("Find list of all employees sorted based on their salaries in descending order");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(System.out::println);
        System.out.println();


        // min / max
        System.out.println("Find employee who is getting the highest salary");
        Employee highestPaidEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
        System.out.println(highestPaidEmployee);
        System.out.println();


        System.out.println("Find employee who is getting the lowest salary");
        Employee lowestPaidEmployee = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
        System.out.println(lowestPaidEmployee);
        System.out.println();


        // group by
        System.out.println("Group employees by gender");
        Map<Employee.Gender, List<Employee>> employeesGroupedByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender));
        System.out.println(employeesGroupedByGender);
        // This is a map of type Gender -> [Employees]
        System.out.println();


        System.out.println("Group employee names by gender");
        Map<Employee.Gender, List<String>> employeesNamesGroupedByGender = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getGender,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        System.out.println(employeesNamesGroupedByGender);
        // This is a map of type Gender -> [Names]
        System.out.println();


        System.out.println("Find counts of employees belonging to each genders");
        Map<Employee.Gender, Long> employeesCountGroupedByGender = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getGender,
                        Collectors.counting()
                ));
        System.out.println(employeesCountGroupedByGender);
        // This is a map of type Gender -> [Count]
        System.out.println();
    }
}
