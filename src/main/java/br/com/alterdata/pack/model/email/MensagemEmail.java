package br.com.alterdata.pack.model.email;

import java.util.List;

public class MensagemEmail {

    private String assunto;
	private String conteudo;
	private String remetente;
	private List<String> destinatarios;
		
	public MensagemEmail(String assunto, String conteudo, String remetente, List<String> destinatarios) {
		this.assunto = assunto;
		this.conteudo = conteudo;
		this.remetente = remetente;
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}
	
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public String getRemetente() {
		return remetente;
	}
	
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public List<String> getDestinatarios() {
		return destinatarios;
	}
	
	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}	
    
}
