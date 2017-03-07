package br.gov.ce.detran.jasper;


import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;

//import detran.cache.CacheFactory;

/**
 * Gerador generico de relatorios PDF iReport - Outubro/2013
 * 
 * @author danieloliveira
 * 
 */
public class GeradorRelatorio {
	
private static final Logger logger = Logger.getLogger(GeradorRelatorio.class);
	
	/**
	 * Método que exibe o relatório no navegador
	 * @param report
	 * @param parametros
	 * @param sc
	 * @param request
	 * @param response
	 * @param nomeRelatorio
	 * @throws IOException
	 */
	public static void emiteRelatorio(String report, Connection con,Map parametros, ServletContext sc, 
							HttpServletRequest request, HttpServletResponse response , String nomeRelatorio) throws IOException{
		String pathContext = sc.getRealPath("/");
		parametros.put("IMG_PATH", getPath(pathContext, "img/logo_detran.jpg"));
		byte[] bytes  = getStreamReport(getPath(pathContext, report) , con, parametros);
		
		streamToFile(bytes, nomeRelatorio , request, response);	
	}

	/**
	 * Metodo que retorna os bytes para a geracao do Relatorio
	 * 
	 * @param pathReport
	 * @param parametros
	 * @return
	 * @throws IOException
	 */
	public static byte[] getStreamReport(String pathReport, Connection con, Map parametros)
			throws IOException {
		
		//new CacheFactory().createDefaultCache();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		String dataEmissao = dateFormat.format(new Date());

		logger.debug("Gerando relatorio...");

		JasperReport report = null;
		try {
			report = JasperCompileManager.compileReport(pathReport);
			//JasperDesign design = JRXmlLoader.load(pathReport); 
			//parametros.put("REPORT_CONNECTION", con);
			//report = JasperCompileManager.compileReport(design); 
		} catch (JRException e) {
			e.printStackTrace();
		}

		JasperPrint print = null;
		try {
			//print = JasperFillManager.fillReport(report, parametros, con);
			print = JasperFillManager.fillReport(report, parametros);
		} catch (JRException e) {
			e.printStackTrace();
		}

		// Exportando relatorio para formato PDF
		byte[] buffer = null;
		try {
			buffer = JasperExportManager.exportReportToPdf(print);
		} catch (JRException e) {
			e.printStackTrace();
		}
		logger.debug("Buffer gerado: " + dataEmissao);
		return buffer;
	}

	static public boolean streamToFile(byte[] buffer, String nomeRelatorio, HttpServletRequest request, HttpServletResponse response) {
		if ((buffer != null) && (buffer.length > 1024)) {
        
			ServletOutputStream outputStream = null;
			try {
				outputStream = response.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline;filename=\"" + nomeRelatorio + "\"");
			response.setHeader("Content-transfer-encoding", "binary");
			response.setContentLength(buffer.length);
			
			try {
				outputStream.write(buffer);
				outputStream.close();
				outputStream.flush();
				request.setAttribute("EMISSAO_RELATORIO_JASPER", "true");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return true;
			
		}else {
			request.setAttribute("MSGALERT", "Não existe resultado para esta consulta!");
			request.setAttribute("TIPOALERTA", "alert-warning");
			return false;
		}
	}
	
	protected static String getPath(String path, String file) {
		return path + "/WEB-INF/" + file;
	}

}
