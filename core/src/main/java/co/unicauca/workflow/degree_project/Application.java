package co.unicauca.workflow.degree_project;

import co.unicauca.workflow.degree_project.plugin.manager.ReportPluginManager;
import co.unicauca.workflow.degree_project.presentation.Console;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
        public static void main(String[] args) {

        // Inicializar el plugin manager con la ruta base de la aplicación.
        String basePath = getBaseFilePath();
        try {
            ReportPluginManager.init(basePath);

            Console presentationObject = new Console();
            presentationObject.start();

        } catch (Exception ex) {
            Logger.getLogger("Application").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }
    }

    /**
     * Obtiene la ruta base donde está corriendo la aplicación,
     * sin importar que sea desde un archivo .class o desde un paquete .jar.
     */
    private static String getBaseFilePath() {
        try {
            String path = Application.class.getProtectionDomain()
                                           .getCodeSource()
                                           .getLocation()
                                           .getPath();
            path = URLDecoder.decode(path, "UTF-8"); // Soluciona espacios y caracteres especiales
            File pathFile = new File(path);

            if (pathFile.isFile()) {
                path = pathFile.getParent();

                if (!path.endsWith(File.separator)) {
                    path += File.separator;
                }
            }

            return path;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Application.class.getName())
                  .log(Level.SEVERE, "Error al eliminar espacios en la ruta del archivo", ex);
            return null;
        }
    }

}
