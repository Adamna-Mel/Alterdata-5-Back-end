package br.com.alterdata.pack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Recurso não foi encontrado");
	}
	
	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}	
	
}
