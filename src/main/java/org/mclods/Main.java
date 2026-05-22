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

        // findFirst
        System.out.println("Find the first element from the stream");
        Optional<Employee> firstEmployee = employees.stream().findFirst();
        System.out.println(firstEmployee.orElse(null));
        System.out.println();


        // findFirst with filter
        System.out.println("Find one employee from Development Department");
        Optional<Employee> firstEmployeeFromDepartment = employees
                .stream()
                .filter(employee -> employee.getDepartment().equals("Development"))
                .findFirst();
        System.out.println(firstEmployeeFromDepartment);
        System.out.println();


        // findAny
        System.out.println("Find any employee from Development Department");
        Optional<Employee> anyEmployeeFromDepartment = employees
                .stream()
                .filter(employee -> employee.getDepartment().equals("Development"))
                .findAny();
        System.out.println(anyEmployeeFromDepartment);
        System.out.println();


        // anyMatch(Predicate), allMatch(Predicate), noneMatch(Predicate) - all these takes a Predicate similar to filter()
        System.out.println("Check if any employee belongs to development department");
        boolean developmentDeptHasEmployees = employees
                .stream()
                .anyMatch(employee -> employee.getDepartment().equals("Development"));
        System.out.println(developmentDeptHasEmployees);
        System.out.println();


        System.out.println("Check if any employee belongs to cooking department");
        boolean cookingDeptHasEmployees = employees
                .stream()
                .anyMatch(employee -> employee.getDepartment().equals("Cooking"));
        System.out.println(cookingDeptHasEmployees);
        System.out.println();


        System.out.println("Check if all employees belongs to development department");
        boolean allEmployeesBelongToDevelopmentDept = employees
                .stream()
                .allMatch(employee -> employee.getDepartment().equals("Cooking"));
        System.out.println(allEmployeesBelongToDevelopmentDept);
        System.out.println();


        System.out.println("Check if all employees have salary greater than 50000");
        boolean allEmployeesHaveSalaryMoreThan50000 = employees
                .stream()
                .allMatch(employee -> employee.getSalary() > 50000);
        System.out.println(allEmployeesHaveSalaryMoreThan50000);
        System.out.println();


        System.out.println("Check if there are no employees in the HR Department");
        boolean hrDepartmentHasNoEmployees = employees
                .stream()
                .noneMatch(employee -> employee.getDepartment().equals("HR"));
        System.out.println(hrDepartmentHasNoEmployees);
        System.out.println();


        System.out.println("Check if there are no employees in the Cooking Department");
        boolean cookingDepartmentHasNoEmployees = employees
                .stream()
                .noneMatch(employee -> employee.getDepartment().equals("Cooking"));
        System.out.println(cookingDepartmentHasNoEmployees);
        System.out.println();


        // limit(long)
        System.out.println("Find names of top 3 highest paid employees");
        List<String> top3HighestPaidEmployees = employees
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .map(Employee::getName)
                .toList();
        System.out.println(top3HighestPaidEmployees);
        System.out.println();


        // skip(long) - skip first n employees - used for pagination mostly
        System.out.println("Show the names of all employees by skipping first 5");
        List<String> employeeListFirst5Skipped = employees.stream().map(Employee::getName).skip(5).toList();
        System.out.println(employeeListFirst5Skipped);
        System.out.println();
    }
}
