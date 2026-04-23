package org.mclods.db;

import org.mclods.entities.Employee;
import org.mclods.entities.Employee.Gender;
import org.mclods.entities.Project;

import java.util.List;

public class EmployeeDB {
    public static List<Employee> findAllEmployees() {
        // Projects
        Project p1 = new Project("P001", "Alpha", "ABC Corp", "Alice");
        Project p2 = new Project("P002", "Beta", "XYZ Ltd", "Bob");
        Project p3 = new Project("P003", "Gamma", "ABC Corp", "Alice");
        Project p4 = new Project("P004", "Delta", "TechWorld", "Charlie");
        Project p5 = new Project("P005", "Epsilon", "MoneyMatters", "Daniel");
        Project p6 = new Project("P006", "Zeta", "SmartTech", "Eva");
        Project p7 = new Project("P007", "Eta", "BrandBoost", "George");
        Project p8 = new Project("P008", "Theta", "InnoSoft", "Hannah");
        Project p9 = new Project("P009", "Iota", "FastTrack", "Ian");
        Project p10 = new Project("P010", "Kappa", "DigitalWave", "Jessica");

        // Employees
        Employee e1 = new Employee(1, "John Doe", "Development", List.of(p1, p2), 80000, Gender.Male);
        Employee e2 = new Employee(2, "Jane Smith", "Development", List.of(p3), 80000, Gender.Female);
        Employee e3 = new Employee(3, "Robert Brown", "Sales", List.of(p4), 60000, Gender.Male);
        Employee e4 = new Employee(4, "Lisa White", "HR", List.of(p1), 55000, Gender.Female);
        Employee e5 = new Employee(5, "Michael Green", "Finance", List.of(p5), 90000, Gender.Male);
        Employee e6 = new Employee(6, "Sophia Brown", "Development", List.of(p6), 85000, Gender.Female);
        Employee e7 = new Employee(7, "James Wilson", "Marketing", List.of(p7), 72000, Gender.Male);
        Employee e8 = new Employee(8, "Olivia Harris", "Development", List.of(p8), 88000, Gender.Female);
        Employee e9 = new Employee(9, "William Lee", "Sales", List.of(p9), 78000, Gender.Male);
        Employee e10 = new Employee(10, "Emily Clark", "Development", List.of(p10), 95000, Gender.Female);


        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }
}
