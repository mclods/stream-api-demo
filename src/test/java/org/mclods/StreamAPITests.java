package org.mclods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mclods.db.EmployeeDB;
import org.mclods.entities.Employee;
import org.mclods.entities.Project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamAPITests {
    private final ByteArrayOutputStream testOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;
    private final List<Employee> employees = EmployeeDB.findAllEmployees();

    @BeforeEach
    void beforeEach() {
        System.setOut(new PrintStream(testOutputStream));
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOutputStream);
    }

    private String getConsoleOutput() {
        return testOutputStream.toString().replaceAll(System.lineSeparator(), "\n");
    }

    @Test
    @DisplayName("Find and print all employees")
    void printAllEmployees() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=3, name=Robert Brown, department=Sales, projects=[Project(projectCode=P004, name=Delta, client=TechWorld, leadName=Charlie)], salary=60000.0, gender=Male)
                Employee(id=4, name=Lisa White, department=HR, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice)], salary=55000.0, gender=Female)
                Employee(id=5, name=Michael Green, department=Finance, projects=[Project(projectCode=P005, name=Epsilon, client=MoneyMatters, leadName=Daniel)], salary=90000.0, gender=Male)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=7, name=James Wilson, department=Marketing, projects=[Project(projectCode=P007, name=Eta, client=BrandBoost, leadName=George)], salary=72000.0, gender=Male)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=9, name=William Lee, department=Sales, projects=[Project(projectCode=P009, name=Iota, client=FastTrack, leadName=Ian)], salary=78000.0, gender=Male)
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        employees.forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find and print all employees using streams")
    void printAllEmployeesUsingStreams() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=3, name=Robert Brown, department=Sales, projects=[Project(projectCode=P004, name=Delta, client=TechWorld, leadName=Charlie)], salary=60000.0, gender=Male)
                Employee(id=4, name=Lisa White, department=HR, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice)], salary=55000.0, gender=Female)
                Employee(id=5, name=Michael Green, department=Finance, projects=[Project(projectCode=P005, name=Epsilon, client=MoneyMatters, leadName=Daniel)], salary=90000.0, gender=Male)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=7, name=James Wilson, department=Marketing, projects=[Project(projectCode=P007, name=Eta, client=BrandBoost, leadName=George)], salary=72000.0, gender=Male)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=9, name=William Lee, department=Sales, projects=[Project(projectCode=P009, name=Iota, client=FastTrack, leadName=Ian)], salary=78000.0, gender=Male)
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        employees.stream().forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find employees who work in the development department")
    void findEmployeesInDevelopmentDepartment() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        employees.stream()
                .filter(e -> e.getDepartment().equals("Development"))
                .forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find count of employees who work in the development department")
    void findCountOfEmployeesInDevelopmentDepartment() {
        long count = employees.stream().filter(e -> e.getDepartment().equals("Development")).count();
        assertThat(count).isEqualTo(5);
    }

    @Test
    @DisplayName("Find employees who work in the development department and whose salary is greater than 12000")
    void findEmployeesFilteredWithMultipleConditions() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Return a list of employees who work in the development department and whose salary is greater than 12000")
    void returnListOfEmployeesFilteredWithMultipleConditions() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        List<Employee> devEmployeesList = employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .collect(Collectors.toList());

        devEmployeesList.forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Return a set of employees who work in the development department and whose salary is greater than 12000")
    void returnSetOfEmployeesFilteredWithMultipleConditions() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        Set<Employee> devEmployeesSet = employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .collect(Collectors.toSet());

        NavigableSet<Employee> sortedDevEmployeeSet = new TreeSet<>(Comparator.comparingInt(Employee::getId));
        sortedDevEmployeeSet.addAll(devEmployeesSet);
        sortedDevEmployeeSet.forEach(System.out::println);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Return a map of employees (id as key and name as value) who work in the development department and whose salary is greater than 12000")
    void returnMapOfEmployeesFilteredWithMultipleConditions() {
        String expectedOutput = """
                {1=John Doe, 2=Jane Smith, 6=Sophia Brown, 8=Olivia Harris, 10=Emily Clark}
                """;

        Map<Integer, String> devEmployeesMap = employees.stream()
                .filter(e -> e.getDepartment().equals("Development") && e.getSalary() > 12000)
                .collect(Collectors.toMap(Employee::getId, Employee::getName));

        NavigableMap<Integer, String> sortedDevEmployeesMap = new TreeMap<>(devEmployeesMap);
        System.out.println(sortedDevEmployeesMap);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Return a list of all employee ids")
    void returnListOfEmployeesIds() {
        String expectedOutput = """
                1
                2
                3
                4
                5
                6
                7
                8
                9
                10
                """;

        List<Integer> employeeIds = employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        employeeIds.forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Return a list of the all the departments")
    void returnListOfDepartments() {
        String expectedOutput = """
                Development
                Development
                Sales
                HR
                Finance
                Development
                Marketing
                Development
                Sales
                Development
                """;

        List<String> employeeDepartmentsList = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toList());

        employeeDepartmentsList.forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Return a set of unique departments")
    void returnSetOfDepartments() {
        String expectedOutput = """
                Development
                Finance
                HR
                Marketing
                Sales
                """;

        Set<String> employeeDepartmentsSet = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());

        NavigableSet<String> sortedEmployeeDepartmentsSet = new TreeSet<>(employeeDepartmentsSet);
        sortedEmployeeDepartmentsSet.forEach(System.out::println);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Return a list of unique departments using distinct")
    void returnListOfUniqueDepartments() {
        String expectedOutput = """
                Development
                Sales
                HR
                Finance
                Marketing
                """;

        List<String> employeeDepartmentsListNoDuplicates = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());

        employeeDepartmentsListNoDuplicates.forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find the list of all the projects that employees work on (ONE TO MANY RELATIONSHIP)")
    void findAllProjects() {
        String expectedOutput = """
                [[Alpha, Beta], [Gamma], [Delta], [Alpha], [Epsilon], [Zeta], [Eta], [Theta], [Iota], [Kappa]]
                """;

        List<List<String>> projectsListofList = employees.stream()
                .map(e -> e.getProjects().stream()
                        .map(Project::getName).collect(Collectors.toList())
                ).collect(Collectors.toList());

        // This is List<List<String>> to obtain a List<String> we need to use a flatMap
        System.out.println(projectsListofList);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find the list of all the projects that employees work on (ONE TO MANY RELATIONSHIP) using flat map")
    void findListOfAllProjects() {
        String expectedOutput = """
                [Alpha, Beta, Gamma, Delta, Alpha, Epsilon, Zeta, Eta, Theta, Iota, Kappa]
                """;

        List<String> projectsList = employees.stream()
                .flatMap(e -> e.getProjects().stream())
                .map(Project::getName)
                .collect(Collectors.toList());

        System.out.println(projectsList);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find the list of all the projects (no duplicates) that employees work on (ONE TO MANY RELATIONSHIP)")
    void findListOfAllUniqueProjects() {
        String expectedOutput = """
                [Alpha, Beta, Gamma, Delta, Epsilon, Zeta, Eta, Theta, Iota, Kappa]
                """;

        List<String> distinctprojectsList = employees.stream()
                .flatMap(e -> e.getProjects().stream())
                .map(Project::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(distinctprojectsList);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find list of all employees sorted in ascending order based on their salaries")
    void findEmployeesSortedBySalaryAsc() {
        String expectedOutput = """
                Employee(id=4, name=Lisa White, department=HR, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice)], salary=55000.0, gender=Female)
                Employee(id=3, name=Robert Brown, department=Sales, projects=[Project(projectCode=P004, name=Delta, client=TechWorld, leadName=Charlie)], salary=60000.0, gender=Male)
                Employee(id=7, name=James Wilson, department=Marketing, projects=[Project(projectCode=P007, name=Eta, client=BrandBoost, leadName=George)], salary=72000.0, gender=Male)
                Employee(id=9, name=William Lee, department=Sales, projects=[Project(projectCode=P009, name=Iota, client=FastTrack, leadName=Ian)], salary=78000.0, gender=Male)
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=5, name=Michael Green, department=Finance, projects=[Project(projectCode=P005, name=Epsilon, client=MoneyMatters, leadName=Daniel)], salary=90000.0, gender=Male)
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find list of all employees sorted based on their salaries in descending order")
    void findEmployeesSortedBySalaryDesc() {
        String expectedOutput = """
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                Employee(id=5, name=Michael Green, department=Finance, projects=[Project(projectCode=P005, name=Epsilon, client=MoneyMatters, leadName=Daniel)], salary=90000.0, gender=Male)
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female)
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female)
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female)
                Employee(id=9, name=William Lee, department=Sales, projects=[Project(projectCode=P009, name=Iota, client=FastTrack, leadName=Ian)], salary=78000.0, gender=Male)
                Employee(id=7, name=James Wilson, department=Marketing, projects=[Project(projectCode=P007, name=Eta, client=BrandBoost, leadName=George)], salary=72000.0, gender=Male)
                Employee(id=3, name=Robert Brown, department=Sales, projects=[Project(projectCode=P004, name=Delta, client=TechWorld, leadName=Charlie)], salary=60000.0, gender=Male)
                Employee(id=4, name=Lisa White, department=HR, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice)], salary=55000.0, gender=Female)
                """;

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find employee who is getting the highest salary")
    void findEmployeeWithHighestSalary() {
        String expectedOutput = """
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)
                """;

        Employee highestPaidEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);

        System.out.println(highestPaidEmployee);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find employee who is getting the lowest salary")
    void findEmployeeWithLowestSalary() {
        String expectedOutput = """
                Employee(id=4, name=Lisa White, department=HR, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice)], salary=55000.0, gender=Female)
                """;

        Employee lowestPaidEmployee = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary)).orElse(null);

        System.out.println(lowestPaidEmployee);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Group employees by gender")
    void findEmployeesGroupedByGender() {
        String expectedOutput = """
                {\
                Male=[\
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male), \
                Employee(id=3, name=Robert Brown, department=Sales, projects=[Project(projectCode=P004, name=Delta, client=TechWorld, leadName=Charlie)], salary=60000.0, gender=Male), \
                Employee(id=5, name=Michael Green, department=Finance, projects=[Project(projectCode=P005, name=Epsilon, client=MoneyMatters, leadName=Daniel)], salary=90000.0, gender=Male), \
                Employee(id=7, name=James Wilson, department=Marketing, projects=[Project(projectCode=P007, name=Eta, client=BrandBoost, leadName=George)], salary=72000.0, gender=Male), \
                Employee(id=9, name=William Lee, department=Sales, projects=[Project(projectCode=P009, name=Iota, client=FastTrack, leadName=Ian)], salary=78000.0, gender=Male)\
                ], \
                Female=[\
                Employee(id=2, name=Jane Smith, department=Development, projects=[Project(projectCode=P003, name=Gamma, client=ABC Corp, leadName=Alice)], salary=80000.0, gender=Female), \
                Employee(id=4, name=Lisa White, department=HR, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice)], salary=55000.0, gender=Female), \
                Employee(id=6, name=Sophia Brown, department=Development, projects=[Project(projectCode=P006, name=Zeta, client=SmartTech, leadName=Eva)], salary=85000.0, gender=Female), \
                Employee(id=8, name=Olivia Harris, department=Development, projects=[Project(projectCode=P008, name=Theta, client=InnoSoft, leadName=Hannah)], salary=88000.0, gender=Female), \
                Employee(id=10, name=Emily Clark, department=Development, projects=[Project(projectCode=P010, name=Kappa, client=DigitalWave, leadName=Jessica)], salary=95000.0, gender=Female)\
                ]\
                }
                """;

        // This is a map of type Gender -> [Employees]
        Map<Employee.Gender, List<Employee>> employeesGroupedByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender));

        NavigableMap<Employee.Gender, List<Employee>> sortedEmployeesGroupedByGender = new TreeMap<>(employeesGroupedByGender);
        System.out.println(sortedEmployeesGroupedByGender);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Group employee names by gender")
    void findEmployeeNamesGroupedByGender() {
        String expectedOutput = """
                {\
                Male=[John Doe, Robert Brown, Michael Green, James Wilson, William Lee], \
                Female=[Jane Smith, Lisa White, Sophia Brown, Olivia Harris, Emily Clark]\
                }
                """;

        // This is a map of type Gender -> [Names]
        Map<Employee.Gender, List<String>> employeesNamesGroupedByGender = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getGender,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));

        NavigableMap<Employee.Gender, List<String>> sortedEmployeesNamesGroupedByGender = new TreeMap<>(employeesNamesGroupedByGender);
        System.out.println(sortedEmployeesNamesGroupedByGender);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find counts of employees belonging to each genders")
    void findCountsOfEmployeesGroupedByGender() {
        String expectedOutput = """
                {Male=5, Female=5}
                """;

        // This is a map of type Gender -> [Count]
        Map<Employee.Gender, Long> employeesCountGroupedByGender = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getGender,
                        Collectors.counting()
                ));

        NavigableMap<Employee.Gender, Long> sortedEmployeesCountGroupedByGender = new  TreeMap<>(employeesCountGroupedByGender);
        System.out.println(sortedEmployeesCountGroupedByGender);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find the first element from the stream")
    void findFirstEmployee() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                """;

        Employee firstEmployee = employees.stream().findFirst().orElse(null);
        System.out.println(firstEmployee);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find first employee from Development department")
    void findFirstEmployeeWithFilter() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                """;

        Employee firstEmployeeFromDepartment = employees
                .stream()
                .filter(employee -> employee.getDepartment().equals("Development"))
                .findFirst()
                .orElse(null);
        System.out.println(firstEmployeeFromDepartment);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Find any employee from Development department")
    void findAnyEmployeeWithFilter() {
        String expectedOutput = """
                Employee(id=1, name=John Doe, department=Development, projects=[Project(projectCode=P001, name=Alpha, client=ABC Corp, leadName=Alice), Project(projectCode=P002, name=Beta, client=XYZ Ltd, leadName=Bob)], salary=80000.0, gender=Male)
                """;

        Employee anyEmployeeFromDepartment = employees
                .stream()
                .filter(employee -> employee.getDepartment().equals("Development"))
                .findAny()
                .orElse(null);
        System.out.println(anyEmployeeFromDepartment);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    // anyMatch(Predicate), allMatch(Predicate), noneMatch(Predicate) - all these takes a Predicate similar to filter()
    @Test
    @DisplayName("Check if any employee belongs to development department")
    void checkIfAnyEmployeeBelongsToDevelopmentDepartment() {
        boolean developmentDeptHasEmployees = employees
                .stream()
                .anyMatch(employee -> employee.getDepartment().equals("Development"));

        assertThat(developmentDeptHasEmployees).isTrue();
    }

    @Test
    @DisplayName("Check if any employee belongs to cooking department")
    void checkIfAnyEmployeeBelongsToCookingDepartment() {
        boolean cookingDeptHasEmployees = employees
                .stream()
                .anyMatch(employee -> employee.getDepartment().equals("Cooking"));

        assertThat(cookingDeptHasEmployees).isFalse();
    }

    @Test
    @DisplayName("Check if all employees belongs to development department")
    void checkIfAllEmployeesBelongsToDevelopmentDepartment() {
        boolean allEmployeesBelongToDevelopmentDept = employees
                .stream()
                .allMatch(employee -> employee.getDepartment().equals("Development"));

        assertThat(allEmployeesBelongToDevelopmentDept).isFalse();
    }

    @Test
    @DisplayName("Check if all employees have salary greater than 50000")
    void checkIfAllEmployeesHaveSalaryGreaterThan50000() {
        boolean allEmployeesHaveSalaryMoreThan50000 = employees
                .stream()
                .allMatch(employee -> employee.getSalary() > 50000);

        assertThat(allEmployeesHaveSalaryMoreThan50000).isTrue();
    }

    @Test
    @DisplayName("Check if there are no employees in the HR Department")
    void checkIfThereAreNoEmployeesInTheHRDepartment() {
        boolean hrDepartmentHasNoEmployees = employees
                .stream()
                .noneMatch(employee -> employee.getDepartment().equals("HR"));

        assertThat(hrDepartmentHasNoEmployees).isFalse();
    }

    @Test
    @DisplayName("Check if there are no employees in the Cooking Department")
    void checkIfThereAreNoEmployeesInTheCookingDepartment() {
        boolean cookingDepartmentHasNoEmployees = employees
                .stream()
                .noneMatch(employee -> employee.getDepartment().equals("Cooking"));

        assertThat(cookingDepartmentHasNoEmployees).isTrue();
    }

    @Test
    @DisplayName("Find names of top 3 highest paid employees")
    void findTop3HighestPaidEmployees() {
        String expectedOutput = """
                Emily Clark
                Michael Green
                Olivia Harris
                """;

        List<String> top3HighestPaidEmployees = employees
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .map(Employee::getName)
                .toList();

        top3HighestPaidEmployees.forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("Show the names of all employees by skipping first 5")
    void showAllEmployeesBySkippingFirst5() {
        String expectedOutput = """
                Sophia Brown
                James Wilson
                Olivia Harris
                William Lee
                Emily Clark
                """;

        // skip(long) - skip first n employees - used for pagination mostly
        List<String> employeeListFirst5Skipped = employees.stream().map(Employee::getName).skip(5).toList();

        employeeListFirst5Skipped.forEach(System.out::println);
        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }
}
