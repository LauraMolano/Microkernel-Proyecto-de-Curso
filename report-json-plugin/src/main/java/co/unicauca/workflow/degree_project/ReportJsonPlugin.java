package co.unicauca.workflow.degree_project;

import co.unicauca.workflow.degree_project.common.entities.Project;
import co.unicauca.workflow.degree_ptoject.common.interfaces.IReportPlugin;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

public class ReportJsonPlugin implements IReportPlugin {

    private static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String generateReport(List<Project> data) {
        StringJoiner joiner = new StringJoiner(",\n", "[\n", "\n]");

        for (Project p : data) {
            String fechaStr = (p.getFechaAprobacionFormatoA() != null)
                    ? DMY.format(p.getFechaAprobacionFormatoA()) : null;

            String est1 = null, est2 = null;
            if (p.getEstudiantes() != null && !p.getEstudiantes().isEmpty()) {
                est1 = p.getEstudiantes().get(0);
                if (p.getEstudiantes().size() > 1) {
                    est2 = p.getEstudiantes().get(1);
                }
            }

            String obj = "  {\n"
                    + "    \"id\": " + p.getId() + ",\n"
                    + "    \"nombre\": " + j(p.getNombreProyecto()) + ",\n"
                    + "    \"fechaFormatoA\": " + j(fechaStr) + ",\n"
                    + "    \"estudiante1\": " + j(est1) + ",\n"
                    + "    \"estudiante2\": " + j(est2) + ",\n"
                    + "    \"profesor\": " + j(p.getProfesor()) + ",\n"
                    + "    \"tipo\": " + j(p.getTipo()) + ",\n"
                    + "    \"programa\": " + j(p.getPrograma()) + "\n"
                    + "  }";
            joiner.add(obj);
        }
        return joiner.toString();
    }

    private static String j(String s) {
        return (s == null) ? "null" : "\"" + escJson(s) + "\"";
    }

    private static String escJson(String s) {
        StringBuilder out = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                    out.append("\\\"");
                    break;
                case '\\':
                    out.append("\\\\");
                    break;
                case '\b':
                    out.append("\\b");
                    break;
                case '\f':
                    out.append("\\f");
                    break;
                case '\n':
                    out.append("\\n");
                    break;
                case '\r':
                    out.append("\\r");
                    break;
                case '\t':
                    out.append("\\t");
                    break;
                default:
                    if (c < 0x20) {
                        out.append("\\u");
                        String hex = Integer.toHexString(c);
                        for (int k = hex.length(); k < 4; k++) {
                            out.append('0');
                        }
                        out.append(hex);
                    } else {
                        out.append(c);
                    }
            }
        }
        return out.toString();
    }
}
