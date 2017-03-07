package br.gov.ce.detran.jasper;

import java.util.List;

import br.gov.ce.detran.model.Tramitacao;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class RelatorioJasper {

private String path; //Caminho base
	
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
	public RelatorioJasper() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "resources/jasper/";
		System.out.println(path);
		System.out.println(this.pathToReportPackage);
	}
	
	
	//Imprime/gera uma lista de Clientes
	public void imprimir(List<Tramitacao> tramitacoes) throws Exception	
	{
		//INV0001
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "RelatorioTramitacao.jrxml");
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(tramitacoes));
 	
		
		JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\adriano.almeida\\Downloads\\Relatorio_de_Clientes.pdf");		
	}
	
	public byte[] gerarBytes(List<Tramitacao> tramitacoes) throws Exception	
	{
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "RelatorioTramitacao.jrxml");
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(tramitacoes));
 
		byte[] retornoEmBytes = JasperExportManager.exportReportToPdf(print);
		
		return retornoEmBytes;
	}
 
	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}
	
	public String getPath() {
		return this.path;
	}
	
}
