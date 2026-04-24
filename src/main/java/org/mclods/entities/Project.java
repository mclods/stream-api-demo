package org.mclods.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String projectCode;

    private String name;

    private String client;

    private String leadName;
}
