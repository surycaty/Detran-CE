package br.gov.ce.detran.bpm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.BreakType;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.util.Base64;

import br.gov.ce.detran.util.Util;


/**
 * <h1>BPM - Gerar Documento</h1>
 * Gerar Documentos a partir de modelos predefinidos.<br />
 * <b>Nota:</b> Os documentos são armazenados no ECM Document do IBM BPM.<br /><br />
 * 
 * @author Eric Silva
 * @version 1.1
 *
 */
public class GerarDocumento {

    private static final String MODELO_SOLICITACAO = "resources/modelo_solicitacao.docx";
    private static final String MODELO_TERMO_REFERENCIA = "resources/modelo_termo_referencia.docx";
    private static final String MODELO_EDITAL_CONCORRENCIA = "resources/modelo_edital_concorrencia.docx";
    private static final String MODELO_EDITAL_PREGAO_ELETRONICO = "resources/modelo_edital_pregao_eletronico.docx";
    private static final String MODELO_EDITAL_PREGAO_PRESENCIAL = "resources/modelo_edital_pregao_presencial.docx";
    private static final String MODELO_OFICIO = "resources/modelo_oficio.docx";
    private static final String MODELO_PARECER = "resources/modelo_parecer.docx";
    private static final String MODELO_HOMOLOGACAO_ADJUDICACAO = "resources/modelo_termo_homologacao_adjudicacao.docx";
    private static final String MODELO_HOMOLOGACAO = "resources/modelo_termo_homologacao.docx";
    private static final String MODELO_REVOGACAO = "resources/modelo_termo_revogacao.docx";
    private static final String MODELO_REPERCUSSAO_FINANCEIRA = "resources/modelo_repercussao_financeira.docx";
        
    
	/**
     * 
     * Gerar um Termo de Autorização
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarSolicitacao(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_SOLICITACAO);
    }

    /**
     * 
     * Gerar um Termo de Referência
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarTermoReferencia(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_TERMO_REFERENCIA);
    }
    
    /**
     * 
     * Gerar um Edital de Concorrência
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarEditalConcorrencia(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_EDITAL_CONCORRENCIA);
    }

    /**
     * 
     * Gerar um Edital de Pregão Eletrônico
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarEditalPregaoEletronico(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_EDITAL_PREGAO_ELETRONICO);
    }

    /**
     * 
     * Gerar um Edital de Pregão Presencial
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarEditalPregaoPresencial(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_EDITAL_PREGAO_PRESENCIAL);
    }
    /**
     * 
     * Gerar um Ofício
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarOficio(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_OFICIO);
    }
    
    /**
     * 
     * Gerar um Parecer Técnico
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarParecer(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_PARECER);
    }

    /**
     * 
     * Gerar um Termo de Homologação e Adjudicação
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarHomologacaoAdjudicacao(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_HOMOLOGACAO_ADJUDICACAO);
    }
    
    
    /**
     * 
     * Gerar um Termo de Homologação
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarHomologacao(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_HOMOLOGACAO);
    }
    
    /**
     * 
     * Gerar um Termo de Revogação
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarRevogacao(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_REVOGACAO);
    }
    
    /**
     * 
     * Gerar uma Repercussão Financeira
     * 
     * @param chaves	Variáveis que serão substituídas no MODELO
     * @return String	Da Base64.encode do arquivo gerado
     * @throws InvalidFormatException
     * @throws IOException
     */
    public static String gerarRepercussaoFinanceira(Map<String, String> chaves) throws IOException {
        return gerar(chaves, MODELO_REPERCUSSAO_FINANCEIRA);
    }

    private static String gerar(Map<String, String> chaves, String modelo) {
        try {
        	        	        	
            XWPFDocument doc = new XWPFDocument(carregarModelo(modelo));
            
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs == null) continue;
                for (XWPFRun r : runs) {
                	
                	r.setColor("000000");
                    r.setFontFamily("Arial");
                    r.setFontSize(11);
                    
                    Object text = r.getText(0);
                    if (text == null) continue;
                    for (Map.Entry<String, String> item : chaves.entrySet()) {
                        if (!((String) text).contains(item.getKey())) continue;
                        
                        //Indentando Inicio do Paragrafo
                        //p.setFirstLineIndent(800);
                        
                        text = ((String) text).replace(item.getKey(), Util.quebraLinhaHtmlParaDocx(item.getValue()));
                        
                        String[] texto = ((String)text).split("#NEW_PAGE#");
                        
                        boolean isFirst = true;
                        int cont = 0;
                        for(String s : texto){
                        
                        	if(!isFirst){
                        		r.addBreak(BreakType.PAGE);
                        	}
	                        
	                        r.setText(s, cont++);
	                        
	                        isFirst = false;
                        }

                    }
                }
            }
            
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p2 : cell.getParagraphs()) {
                            for (XWPFRun r : p2.getRuns()) {
                                String text = r.getText(0);
                                if (text == null) continue;
                                for (Map.Entry<String, String> item : chaves.entrySet()) {
                                    if (!text.contains(item.getKey())) continue;
                                    text = text.replace(item.getKey(), item.getValue());
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            doc.write(bos);
            doc.close();
            byte[] bytes = Base64.encode(bos.toByteArray());
            
//            FileOutputStream fosDocx = new FileOutputStream("C://Users//adriano.almeida//Downloads//teste.docx");
//            fosDocx.write(bos.toByteArray());
//            fosDocx.close();

            return new String(bytes);
        }
        catch (Exception e) {
            System.out.println("ERRO >>> " + e.getClass() + " >>> " + e.getMessage());
            return null;
        }
    }
    
    private static InputStream carregarModelo(String resName) {
    	
    	InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(resName);
    	
    	if(input == null) {
    		try {
    			input = new FileInputStream(resName);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
        return input;
    }

}
