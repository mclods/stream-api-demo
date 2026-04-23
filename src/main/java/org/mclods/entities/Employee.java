package org.mclods.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
