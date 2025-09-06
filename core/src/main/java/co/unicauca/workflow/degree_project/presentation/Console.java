package co.unicauca.workflow.degree_project.presentation;

import co.unicauca.workflow.degree_project.business.ProjectService;
import co.unicauca.workflow.degree_project.business.ReportService;
import co.unicauca.workflow.degree_project.common.entities.Project;
import java.util.List;
import java.util.Scanner;

public class Console {
    private ReportService reportService;
    private ProjectService projectService;
    private Scanner scanner;

    public Console() {
        reportService = new ReportService();
        projectService = new ProjectService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        int option;

        System.out.println("=== Aplicacion de Reportes de Proyectos de Grado ===");

        do {
            System.out.println();
            System.out.println("1. Generar reporte.");
            System.out.println("2. Salir.");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    handleReportOption();
                    break;
                case 2:
                    System.out.println("Aplicacion terminada");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (option != 2);
    }

    private void handleReportOption() {
        List<Project> projects = projectService.getAll();

        System.out.println("Seleccione el tipo de reporte a generar: ");
        System.out.println("1. HTML");
        System.out.println("2. JSON");

        int reportType = scanner.nextInt();

        String pluginType = "";

        switch (reportType) {
            case 1:
                pluginType = "html";
                break;
            case 2:
                pluginType = "json";
                break;
            default:
                System.out.println("Opción no valida.");
                return;
        }

        try {
            String report = reportService.generateReport(projects, pluginType);
            System.out.println("=== Reporte Generado ===");
            System.out.println(report);

        } catch (Exception exception) {
            System.out.println("No fue posible generar el reporte. " + exception.getMessage());
        }
    }
}
