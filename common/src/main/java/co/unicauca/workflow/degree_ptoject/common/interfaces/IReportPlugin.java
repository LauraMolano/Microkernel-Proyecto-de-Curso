package co.unicauca.workflow.degree_ptoject.common.interfaces;

import co.unicauca.workflow.degree_project.common.entities.Project;
import java.util.List;

public interface IReportPlugin {
    String generateReport(List<Project> data);
}
