package co.unicauca.workflow.degree_project.business;

import co.unicauca.workflow.degree_project.common.entities.Project;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectService {
    public List<Project> getAll() {

        List<Project> projects = new ArrayList<>();

        Project projectOne = new Project(1, "Sistema de monitoreo del ambiente", LocalDate.of(2025, 7, 12), Arrays.asList("Juan Manuel Ortega", "Andres Gomez"), "Libardo Pantoja", "Investigacion", "Ingenieria telematica");
        Project projectTwo = new Project(2, "Sistema de gestion de alimentos", LocalDate.of(2024, 12, 10), Arrays.asList("Maryuri Fernandez", "Laura Isabel"), "Daniel Perdomo", "Investigacion", "Ingenieria de sistemas");
        Project projectThree = new Project(3, "practicante junior", LocalDate.of(2025, 5, 23), Arrays.asList("Carolina Burbano"), "Erwin Meza", "Practica profesional", "Ingenieria electronica");

        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);

        return projects;
    }
}
