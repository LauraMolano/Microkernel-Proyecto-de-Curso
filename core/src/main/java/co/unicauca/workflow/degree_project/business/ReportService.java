package co.unicauca.workflow.degree_project.business;

import co.unicauca.workflow.degree_project.common.entities.Project;
import co.unicauca.workflow.degree_project.plugin.manager.ReportPluginManager;
import co.unicauca.workflow.degree_ptoject.common.interfaces.IReportPlugin;
import java.util.List;

public class ReportService {
    /**
     * Genera un reporte en el formato especificado (HTML, JSON, etc.)
     * usando los plugins registrados en ReportPluginManager.
     * 
     * @param data  Lista de proyectos
     * @param type  Tipo de reporte ("html", "json", etc.)
     * @return      Reporte generado como String
     * @throws Exception Si no existe plugin para el tipo dado
     */
    public String generateReport(List<Project> data, String type) throws Exception {
        ReportPluginManager manager = ReportPluginManager.getInstance();
        IReportPlugin plugin = manager.getReportPlugin(type);

        if (plugin == null) {
            throw new Exception("No hay un plugin disponible para el formato indicado: " + type);
        }

        return plugin.generateReport(data);
    }
}
