package br.gov.ce.detran.model;

public class Tramitacao {
	
	private String remetente;
	private String dataEnvio;
	private String observavcoesRemetente;
	private String destinatario;
	private String dataRecebimento;
	private String observavcoesDestinatario;
	
	public String getRemetente() {
		return remetente;
	}
	
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public String getDataEnvio() {
		return dataEnvio;
	}
	
	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	public String getObservavcoesRemetente() {
		return observavcoesRemetente;
	}
	
	public void setObservavcoesRemetente(String observavcoesRemetente) {
		this.observavcoesRemetente = observavcoesRemetente;
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getDataRecebimento() {
		return dataRecebimento;
	}
	
	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	public String getObservavcoesDestinatario() {
		return observavcoesDestinatario;
	}
	
	public void setObservavcoesDestinatario(String observavcoesDestinatario) {
		this.observavcoesDestinatario = observavcoesDestinatario;
	}

}
