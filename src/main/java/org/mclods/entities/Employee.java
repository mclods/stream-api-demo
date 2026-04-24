package org.mclods.entities;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;

    private String name;

    private String department;

    private List<Project> projects;

    private double salary;

    private Gender gender;

    public enum Gender {
        Male, Female
    }
}
