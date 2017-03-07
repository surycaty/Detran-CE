package br.gov.ce.detran.bpm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.ce.detran.jasper.GeradorRelatorio;
import br.gov.ce.detran.jasper.RelatorioJasper;
import br.gov.ce.detran.model.Tramitacao;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		System.out.println("INICIO");
		
		
		Map<String, String> chaves = new HashMap<String, String>();
		chaves.put("INV0001", "variavel com novo valor");
		
		
		
		try {
			
			byte[] teste = GeradorRelatorio.getStreamReport("C:\\eclipse\\workspace\\bpm\\src\\resources\\jasper\\RelatorioTramitacao.jrxml", null, null);
			
          FileOutputStream fosDocx = new FileOutputStream("C://Users//adriano.almeida//Downloads//teste.pdf");
          fosDocx.write(teste);
          fosDocx.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try{
			List<Tramitacao> tramitacao = new ArrayList<Tramitacao>();
			
			Tramitacao t1 = new Tramitacao();
			t1.setRemetente("Adriano");
			t1.setDestinatario("Almeida");
			
			tramitacao.add(t1);
			
			RelatorioJasper relatorio = new RelatorioJasper();
			relatorio.imprimir(tramitacao);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}*/
		
		/*
		Map<String, String> chaves = new HashMap<String, String>();
		chaves.put("[GLOSSARIO]", "<p><span style='font-weight: bold;'>Ao Sr.</span></p>#NEW_PAGE#<p><span style='font-weight: bold;'>Dr. Igor Vasconcelos Ponte</span></p>#NEW_PAGE#<p><span style='font-weight: bold;'>Superintendente</span></p><p><span style='font-weight: bold;'>Departamento Estadual de Trânsito</span></p>");
		
		try {
			GerarDocumento.gerarEditalConcorrencia(chaves);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println("FIM");

	}

}
