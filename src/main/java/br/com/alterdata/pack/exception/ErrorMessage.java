package br.com.alterdata.pack.exception;

public class ErrorMessage {

	private String nome;
	private Integer codigo;
	private String mensagem;
		
	public ErrorMessage(String nome, Integer codigo, String mensagem) {
		this.nome = nome;
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}

}