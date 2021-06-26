package org.jefersoncalderon.report;

import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;
import java.io.InputStream;
import javax.swing.JOptionPane;
import org.jefersoncalderon.db.Conexion;

public class GenerarReport {
    public static void mostrarReporte(String NombreReporte, String titulo, Map parametros){
    InputStream reporte = GenerarReport.class.getResourceAsStream(NombreReporte);
    try{
    JasperReport reportemaestro = (JasperReport) JRLoader.loadObject (reporte);
    JasperPrint reporteImpreso = JasperFillManager.fillReport(reportemaestro, parametros, Conexion.getInstance().getConexion());
    JasperViewer visor = new JasperViewer(reporteImpreso, false);
    visor.setTitle(titulo);
    visor.setVisible(true);
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, "No se a podido generar el reporte");
    e.printStackTrace();
    }
    }
}
