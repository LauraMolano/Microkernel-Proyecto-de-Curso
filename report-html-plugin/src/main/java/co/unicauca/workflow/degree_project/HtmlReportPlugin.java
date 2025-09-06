package co.unicauca.workflow.degree_project;

import co.unicauca.workflow.degree_project.common.entities.Project;
import co.unicauca.workflow.degree_ptoject.common.interfaces.IReportPlugin;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HtmlReportPlugin implements IReportPlugin {

    private static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String generateReport(List<Project> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n<html lang=\"es\">\n<head>\n")
                .append("  <meta charset=\"UTF-8\">\n")
                .append("  <title>Reporte de Proyectos de Grado</title>\n")
                .append("  <style>")
                .append("    body{font-family:Arial,Helvetica,sans-serif;margin:16px;}")
                .append("    table{border-collapse:collapse;width:100%;}")
                .append("    th,td{border:1px solid #ccc;padding:8px;text-align:left;}")
                .append("    thead{background:#f3f3f3;font-weight:bold;}")
                .append("  </style>\n")
                .append("</head>\n<body>\n")
                .append("  <h1>Reporte de Proyectos de Grado</h1>\n")
                .append("  <table>\n")
                .append("    <thead>\n      <tr>\n")
                .append("        <th>ID</th>\n")
                .append("        <th>Nombre</th>\n")
                .append("        <th>Fecha aprobacion formato A</th>\n")
                .append("        <th>Estudiante(s)</th>\n")
                .append("        <th>Profesor</th>\n")
                .append("        <th>Tipo</th>\n")
                .append("        <th>Programa</th>\n")
                .append("      </tr>\n    </thead>\n<tbody>\n");

        for (Project p : data) {
            String fecha = p.getFechaAprobacionFormatoA() != null
                    ? DMY.format(p.getFechaAprobacionFormatoA())
                    : "";

            String estudiantes = p.getEstudiantes() == null
                    ? ""
                    : p.getEstudiantes().stream()
                            .filter(Objects::nonNull)
                            .map(HtmlReportPlugin::esc)
                            .collect(Collectors.joining(", "));

            sb.append("      <tr>\n")
                    .append("        <td>").append(esc(String.valueOf(p.getId()))).append("</td>\n")
                    .append("        <td>").append(esc(p.getNombreProyecto())).append("</td>\n")
                    .append("        <td>").append(esc(fecha)).append("</td>\n")
                    .append("        <td>").append(estudiantes).append("</td>\n")
                    .append("        <td>").append(esc(p.getProfesor())).append("</td>\n")
                    .append("        <td>").append(esc(p.getTipo())).append("</td>\n")
                    .append("        <td>").append(esc(p.getPrograma())).append("</td>\n")
                    .append("      </tr>\n");
        }

        sb.append("    </tbody>\n  </table>\n</body>\n</html>\n");
        return sb.toString();
    }

    private static String esc(String s) {
        if (s == null) {
            return "N/A";
        }
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
