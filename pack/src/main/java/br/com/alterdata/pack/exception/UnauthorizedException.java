package br.com.alterdata.pack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class ResourceUnauthorizedException extends RuntimeException {
	
	public ResourceUnauthorizedException() {
		super("Usuário não está autorizado a utilizar esse recurso");
	}
	
	public ResourceUnauthorizedException(String mensagem) {
		super(mensagem);
	}
}
